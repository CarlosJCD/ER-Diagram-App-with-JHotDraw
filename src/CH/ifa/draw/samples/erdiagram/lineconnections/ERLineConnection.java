package CH.ifa.draw.samples.erdiagram.lineconnections;

import CH.ifa.draw.figures.LineConnection;
import CH.ifa.draw.figures.LineDecoration;

public class ERLineConnection extends LineConnection {
    public ERLineConnection(LineDecoration decorationTip1, LineDecoration decorationTip2) {
        setStartDecoration(decorationTip1);
        setEndDecoration(decorationTip2);
    }
}
