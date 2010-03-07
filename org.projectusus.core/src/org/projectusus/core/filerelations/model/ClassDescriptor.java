package org.projectusus.core.filerelations.model;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.eclipse.core.resources.IFile;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.core.dom.ITypeBinding;

public class ClassDescriptor {

    private final IFile file;
    private final Classname classname;
    private final Packagename packagename;

    public ClassDescriptor( IFile file, Classname classname, Packagename packagename ) {
        this.file = file;
        this.classname = classname;
        this.packagename = packagename;
    }

    public ClassDescriptor( ITypeBinding binding ) throws JavaModelException {
        this( (IFile)binding.getJavaElement().getUnderlyingResource(), new Classname( binding.getName() ), new Packagename( binding.getPackage().getName() ) );
    }

    public IFile getFile() {
        return file;
    }

    public Classname getClassname() {
        return classname;
    }

    public Packagename getPackagename() {
        return packagename;
    }

    @Override
    public boolean equals( Object object ) {
        return object instanceof ClassDescriptor && equals( (ClassDescriptor)object );
    }

    @Override
    public int hashCode() {
        HashCodeBuilder builder = new HashCodeBuilder();
        builder.append( file ).append( classname ).append( packagename );
        return builder.toHashCode();
    }

    private boolean equals( ClassDescriptor other ) {
        EqualsBuilder builder = new EqualsBuilder();
        builder.append( file, other.file );
        builder.append( classname, other.classname );
        builder.append( packagename, other.packagename );
        return builder.isEquals();
    }
}
