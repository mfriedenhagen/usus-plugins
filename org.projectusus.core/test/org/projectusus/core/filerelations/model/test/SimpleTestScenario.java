package org.projectusus.core.filerelations.model.test;

import static org.mockito.Mockito.mock;

import org.eclipse.core.resources.IFile;
import org.projectusus.core.filerelations.model.Classname;

public class SimpleTestScenario {

    public static final IFile SOURCE = mock( IFile.class );
    public static final Classname SOURCE_CLASS = new Classname( "sourceClass" ); //$NON-NLS-1$ 

    public static final IFile TARGET = mock( IFile.class );
    public static final Classname TARGET_CLASS = new Classname( "targetClass" ); //$NON-NLS-1$

    public static final IFile ANOTHER_TARGET = mock( IFile.class );
    public static final Classname ANOTHER_TARGET_CLASS = new Classname( "anotherTargetClass" ); //$NON-NLS-1$

}
