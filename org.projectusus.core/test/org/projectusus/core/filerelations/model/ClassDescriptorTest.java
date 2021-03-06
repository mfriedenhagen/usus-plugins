package org.projectusus.core.filerelations.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.junit.matchers.JUnitMatchers.hasItems;
import static org.mockito.Mockito.mock;

import org.eclipse.core.resources.IFile;
import org.junit.Before;
import org.junit.Test;

public class ClassDescriptorTest {

    private IFile file;

    @Before
    public void setup() {
        file = mock( IFile.class );
    }

    @Test
    public void equalsIsEqual() {
        ClassDescriptor descriptor1 = createDescriptor( file );
        Object descriptor2 = createDescriptor( file );
        assertTrue( descriptor1.equals( descriptor2 ) );
        assertSame( descriptor1, descriptor2 );
    }

    @Test
    public void keyComparison() {
        String classNameString = "name"; //$NON-NLS-1$
        Classname classname1 = new Classname( classNameString );
        Classname classname2 = new Classname( classNameString );
        assertEquals( classname1, classname2 );
        assertNotSame( classname1, classname2 );

        Packagename packagename1 = Packagename.of( "packagename", null ); //$NON-NLS-1$
        Packagename packagename2 = Packagename.of( "packagename", null ); //$NON-NLS-1$
        assertEquals( packagename1, packagename2 );
        assertSame( packagename1, packagename2 );

        ClassDescriptorKey key1a = new ClassDescriptorKey( file, classname1, packagename1 );
        ClassDescriptorKey key1b = new ClassDescriptorKey( file, classname1, packagename1 );
        ClassDescriptorKey key2 = new ClassDescriptorKey( file, classname2, packagename2 );
        assertEquals( key1a, key1b );
        assertEquals( key1a, key2 );
        assertEquals( key1b, key2 );
        assertNotSame( key1a, key1b );
        assertNotSame( key1a, key2 );
        assertNotSame( key1b, key2 );

        ClassDescriptor descriptor1a = ClassDescriptor.of( file, classname1, packagename1 );
        ClassDescriptor descriptor1b = ClassDescriptor.of( file, classname1, packagename1 );
        ClassDescriptor descriptor2 = ClassDescriptor.of( file, classname2, packagename2 );
        assertEquals( descriptor1a, descriptor2 );
        assertEquals( descriptor1a, descriptor1b );
        assertEquals( descriptor1b, descriptor2 );
        assertSame( descriptor1a, descriptor2 );
        assertSame( descriptor1a, descriptor1b );
        assertSame( descriptor1b, descriptor2 );
    }

    @Test
    public void classIsAddedToPackageOnCreation() {
        ClassDescriptor descriptor = createDescriptor( file );
        assertTrue( descriptor.getPackagename().containsClass( descriptor ) );
    }

    @Test
    public void classIsRemovedFromPackageOnDestruction() {
        ClassDescriptor descriptor = createDescriptor( file );
        Packagename packagename = descriptor.getPackagename();
        descriptor.removeFromPool();
        assertFalse( packagename.containsClass( descriptor ) );
    }

    @Test
    public void isCrossPackage() {
        ClassDescriptor first = createDescriptor( Packagename.of( "x", null ) ); //$NON-NLS-1$
        ClassDescriptor second = createDescriptor( Packagename.of( "y", null ) ); //$NON-NLS-1$
        first.addChild( second );
        assertThat( first.getChildrenInOtherPackages(), hasItems( second ) );
    }

    @Test
    public void classIsNotAddedToItsChildren() {
        ClassDescriptor descriptor = ClassDescriptor.of( mock( IFile.class ), new Classname( "classname" ), Packagename.of( "name", null ) ); //$NON-NLS-1$ //$NON-NLS-2$
        assertEquals( 0, descriptor.getChildren().size() );
        descriptor.addChild( descriptor );
        assertEquals( 0, descriptor.getChildren().size() );
    }

    private static ClassDescriptor createDescriptor( IFile file ) {
        return ClassDescriptor.of( file, new Classname( "classname1" ), Packagename.of( "packagename1", null ) ); //$NON-NLS-1$ //$NON-NLS-2$
    }

    private static ClassDescriptor createDescriptor( Packagename packagename ) {
        return ClassDescriptor.of( mock( IFile.class ), new Classname( "classname1" ), packagename ); //$NON-NLS-1$
    }

}
