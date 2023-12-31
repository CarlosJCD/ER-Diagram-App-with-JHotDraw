/*
 * @(#)StandardLayouter.java
 *
 * Project:		JHotdraw - a GUI framework for technical drawings
 *				http://www.jhotdraw.org
 *				http://jhotdraw.sourceforge.net
 * Copyright:	 by the original author(s) and all contributors
 * License:		Lesser GNU Public License (LGPL)
 *				http://www.opensource.org/licenses/lgpl-license.html
 */

package CH.ifa.draw.contrib;

import CH.ifa.draw.framework.*;
import CH.ifa.draw.contrib.*;
import java.awt.*;

/**
 * A StandardLayouter contains standard algorithm for
 * layouting a Layoutable. As a standard behaviour
 * all child components of a Layoutable are laid out
 * underneath each other starting from top to bottom while the
 * x position of all child components stays the same and the width
 * is forced to the width of the maximum width. At the end
 * the presentation figure of the Layoutable is
 * set to the maximum x and y size to encompass all contained
 * child components graphically as well.
 *
 * @author	Wolfram Kaiser
 * @version <$CURRENT_VERSION$>
 */
public class StandardLayouter extends SimpleLayouter {

	/**
	 * Default constructor which is needed for the Storable mechanism.
	 * Usually, the constructor which takes a Layoutable
	 * should be used as each StandardLayouter is associated
	 * with exactly one Layoutable.
	 */
	public StandardLayouter() {
		this(null);
	}

	/**
	 * Constructor which associates a StandardLayouter with
	 * a certain Layoutable.
	 *
	 * @param	newLayoutable	Layoutable to be laid out
	 */
	public StandardLayouter(Layoutable newLayoutable) {
		super(newLayoutable);
	}

	/**
	 * Create a new instance of this type and sets the layoutable
	 */
	public Layouter create(Layoutable newLayoutable) {
		return new StandardLayouter(newLayoutable);
	}

	/*
	 * Calculate the layout for the figure and all its subelements. The
	 * layout is not actually performed but just its dimensions are calculated.
	 * Insets are added for all non-top-level figures.
	 *
	 * @param origin start point for the layout
	 * @param corner minimum corner point for the layout
	 */
	public Rectangle calculateLayout(Point origin, Point corner) {
		int maxWidth = Math.abs(corner.x - origin.x);
		int maxHeight = 0;

		// layout enclosed Layoutable and find maximum width
		FigureEnumeration fe = getLayoutable().figures();
		while (fe.hasNextFigure()) {
			Figure currentFigure = fe.nextFigure();
			Rectangle r = null;
			if (currentFigure instanceof Layoutable) {
				Layouter layoutStrategy = ((Layoutable)currentFigure).getLayouter();
				r = layoutStrategy.calculateLayout(
					new Point(0, 0), new Point(0, 0));
				// add insets to calculated rectangle
				r.grow(layoutStrategy.getInsets().left + layoutStrategy.getInsets().right,
						layoutStrategy.getInsets().top + layoutStrategy.getInsets().bottom);
			}
			else {
				r = new Rectangle(currentFigure.displayBox().getBounds());
			}
			maxWidth = Math.max(maxWidth, r.width);
			maxHeight += r.height;
		}

		return new Rectangle(origin.x, origin.y, maxWidth, maxHeight);
	}

	/**
	 * Method which lays out a figure. It is called by the figure
	 * if a layout task is to be performed. First, the layout dimension for
	 * the figure is calculated and then the figure is arranged newly.
	 * All child component are place beneath another. The figure and all
	 * its children are forced to the minimium width
	 *
	 * @param origin start point for the layout
	 * @param corner minimum corner point for the layout
	 */
	public Rectangle layout(Point origin, Point corner) {
		// calculate the layout of the figure and its sub-figures first
		Rectangle r = calculateLayout(origin, corner);

		int maxHeight = getInsets().top;
		FigureEnumeration fe = getLayoutable().figures();
		while (fe.hasNextFigure()) {
			Figure currentFigure = fe.nextFigure();

			Point partOrigin = new Point(r.x + getInsets().left, r.y + maxHeight);

			// Fix for bug request ID 548000
			// Previously was
			//      Point partCorner = new Point(r.x + getInsets().left + r.width, r.y + currentFigure.displayBox().height);
			// The right inset wasn't included in the calculation
			Point partCorner = new Point(r.x + getInsets().left - getInsets().right + r.width, r.y + currentFigure.displayBox().height);
			currentFigure.displayBox(partOrigin, partCorner);

			maxHeight += currentFigure.displayBox().height;
		}

		// the maximum width has been already calculated
		// Fix for bug request ID 548000
		// Previously was
		//      Point partCorner = new Point(r.x + getInsets().left + r.width, r.y + currentFigure.displayBox().height);
		// The right inset wasn't included in the calculation
		return new Rectangle(r.x, r.y, r.width, maxHeight + getInsets().bottom);
	}
}