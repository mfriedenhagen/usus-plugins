// Copyright (c) 2009-2010 by the projectusus.org contributors
// This software is released under the terms and conditions
// of the Eclipse Public License (EPL) 1.0.
// See http://www.eclipse.org/legal/epl-v10.html for details.
package org.projectusus.ui.internal.hotspots.actions;

import static org.eclipse.jdt.core.JavaCore.createCompilationUnitFrom;

import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jface.action.Action;
import org.projectusus.core.basis.Hotspot;
import org.projectusus.ui.internal.DisplayHotspot;
import org.projectusus.ui.util.EditorOpener;

public class OpenHotspotInEditor extends Action {

    private final DisplayHotspot hotspot;

    public OpenHotspotInEditor( DisplayHotspot hotspot ) {
        this.hotspot = hotspot;
    }

    @Override
    public void run() {
        EditorOpener opener = new EditorOpener();
        Hotspot realHotspot = hotspot.getHotspot();
        if( realHotspot == null ) {
            opener.openEditor( hotspot.getFile() );
            return;
        }
        ICompilationUnit compilationUnit = extractCompilationUnit();
        if( compilationUnit != null ) {
            opener.openEditorAt( compilationUnit, realHotspot.getSourcePosition() );
        } else {
            opener.openEditor( hotspot.getFile() );
        }
    }

    private ICompilationUnit extractCompilationUnit() {
        try {
            return createCompilationUnitFrom( hotspot.getFile() );
        } catch( Exception ex ) {
            // The contract says that it returns null when a compilation unit
            // couldn't be loaded (e.g. it's a non-Java file); but actually,
            // the request fires an exception. Well, in any case, we know there
            // is no compilation unit...
            return null;
        }
    }
}
