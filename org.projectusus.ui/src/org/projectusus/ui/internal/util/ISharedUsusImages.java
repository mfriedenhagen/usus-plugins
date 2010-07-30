// Copyright (c) 2009-2010 by the projectusus.org contributors
// This software is released under the terms and conditions
// of the Eclipse Public License (EPL) 1.0.
// See http://www.eclipse.org/legal/epl-v10.html for details.
package org.projectusus.ui.internal.util;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.graphics.Image;
import org.projectusus.ui.internal.UsusUIPlugin;

public interface ISharedUsusImages {

    // prefix all constants with the plugin id
    String ID = UsusUIPlugin.getPluginId();

    String OBJ_CODE_PROPORTIONS = ID + ".OBJ_CODE_PROPORTIONS";
    String OBJ_INFO = ID + ".OBJ_INFO";
    String OBJ_WARNINGS = ID + ".OBJ_WARNINGS";

    String OBJ_LEVELUP = ID + ".OBJ_LEVELUP";
    String OBJ_LEVELDOWN = ID + ".OBJ_LEVELDOWN";

    String VIEW_WARNING = ID + ".VIEW_WARNING";

    Image getImage( String key );

    ImageDescriptor getDescriptor( String key );

    Image getTrendImage( int level );
}
