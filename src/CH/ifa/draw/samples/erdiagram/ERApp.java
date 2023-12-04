/*
 * @(#)NetApp.java
 *
 * Project:		JHotdraw - a GUI framework for technical drawings
 *				http://www.jhotdraw.org
 *				http://jhotdraw.sourceforge.net
 * Copyright:	 by the original author(s) and all contributors
 * License:		Lesser GNU Public License (LGPL)
 *				http://www.opensource.org/licenses/lgpl-license.html
 */

package CH.ifa.draw.samples.erdiagram;

import javax.swing.JToolBar;
import CH.ifa.draw.framework.*;
import CH.ifa.draw.standard.*;
import CH.ifa.draw.figures.*;
import CH.ifa.draw.application.DrawApplication;
import CH.ifa.draw.samples.erdiagram.figures.EntityFigure;
import CH.ifa.draw.samples.erdiagram.lineconnections.ERLineConnection;
import CH.ifa.draw.samples.erdiagram.tips.ManyTip;
import CH.ifa.draw.samples.erdiagram.tips.OneTip;

/**
 * @version <$CURRENT_VERSION$>
 */
public class ERApp extends DrawApplication {

    public ERApp() {
        super("Entity Relationship");
    }

    protected void createTools(JToolBar palette) {
        super.createTools(palette);

        Tool tool = new TextTool(this, new TextFigure());
        palette.add(createToolButton(IMAGES + "TEXT", "Edit Text", tool));

        tool = new ConnectedTextTool(this, new TextFigure());
        palette.add(createToolButton(IMAGES + "ATEXT", "Create Relation Label", tool));

        tool = new CreationTool(this, new EntityFigure());
        palette.add(createToolButton(IMAGES + "ENT", "New Entity", tool));

        tool = new ConnectionTool(this, new ERLineConnection(new OneTip(), new OneTip()));
        palette.add(createToolButton(IMAGES + "OTO", "New 1:1 Relation", tool));

        tool = new ConnectionTool(this, new ERLineConnection(new OneTip(), new ManyTip()));
        palette.add(createToolButton(IMAGES + "OTM", "New 1:n Relation", tool));

        tool = new ConnectionTool(this, new ERLineConnection(new ManyTip(), new OneTip()));
        palette.add(createToolButton(IMAGES + "MTO", "New n:1 Relation", tool));

        tool = new ConnectionTool(this, new ERLineConnection(new ManyTip(), new ManyTip()));
        palette.add(createToolButton(IMAGES + "MTM", "New n:n Relation", tool));

    }

    // -- main -----------------------------------------------------------

    public static void main(String[] args) {
        DrawApplication window = new ERApp();
        window.open();
    }
}
