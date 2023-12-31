/*
 * @(#)Connector.java
 *
 * Project:		JHotdraw - a GUI framework for technical drawings
 *				http://www.jhotdraw.org
 *				http://jhotdraw.sourceforge.net
 * Copyright:	 by the original author(s) and all contributors
 * License:		Lesser GNU Public License (LGPL)
 *				http://www.opensource.org/licenses/lgpl-license.html
 */

package CH.ifa.draw.figures;

import CH.ifa.draw.standard.AbstractConnector;
import CH.ifa.draw.framework.Figure;

public class NullConnector extends AbstractConnector {
	// AbstractConnector implements already all methods but cannot be instantiated

	private NullConnector() {
		// do nothing: for JDO-compliance only
	}

	public NullConnector(Figure owner) {
		super(owner);
	}
}
