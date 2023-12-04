package CH.ifa.draw.samples.erdiagram.tips;

import java.awt.*;

import CH.ifa.draw.figures.AbstractLineDecoration;

public class ManyTip extends AbstractLineDecoration {

    private double length;
    private double crowFootoffset;
    private double perpendicularLineOffset;

    public ManyTip() {
        this(10, 10, 20);
    }

    public ManyTip(double length, double crowFootoffset, double perpendicularLineOffset) {
        setLength(length);
        setCrowFootoffset(crowFootoffset);
        setPerpendicularLineOffset(perpendicularLineOffset);
        setFillColor(Color.yellow);
    }

    @Override
    public Polygon outline(int x1, int y1, int x2, int y2) {

        double dir = Math.atan2(y2 - y1, x2 - x1);

        int offsetX1 = x2 - (int) (crowFootoffset * Math.cos(dir));
        int offsetY1 = y2 - (int) (crowFootoffset * Math.sin(dir));

        int offsetX2 = x2 - (int) (perpendicularLineOffset * Math.cos(dir));
        int offsetY2 = y2 - (int) (perpendicularLineOffset * Math.sin(dir));

        Polygon shape = new Polygon();

        shape.addPoint(
                offsetX1,
                offsetY1);

        double angle = dir + Math.PI / 4;
        shape.addPoint(
                offsetX1 + (int) (length * Math.cos(angle) * 1.25),
                offsetY1 + (int) (length * Math.sin(angle) * 1.25));

        shape.addPoint(
                offsetX1,
                offsetY1);

        angle = dir - Math.PI / 4;
        shape.addPoint(
                offsetX1 + (int) (length * Math.cos(angle) * 1.25),
                offsetY1 + (int) (length * Math.sin(angle) * 1.25));

        shape.addPoint(
                offsetX1,
                offsetY1);

        shape.addPoint(
                offsetX2,
                offsetY2);

        angle = dir - Math.PI / 2;
        shape.addPoint(
                offsetX2 + (int) (length * Math.cos(angle)),
                offsetY2 + (int) (length * Math.sin(angle)));

        shape.addPoint(
                offsetX2,
                offsetY2);

        angle = dir + Math.PI / 2;
        shape.addPoint(
                offsetX2 + (int) (length * Math.cos(angle)),
                offsetY2 + (int) (length * Math.sin(angle)));

        shape.addPoint(
                offsetX2,
                offsetY2);

        return shape;
    }

    protected void setLength(double newLength) {
        length = newLength;
    }

    protected double getLength() {
        return length;
    }

    protected void setCrowFootoffset(double newOffset) {
        crowFootoffset = newOffset;
    }

    protected void setPerpendicularLineOffset(double newOffset) {
        perpendicularLineOffset = newOffset;
    }

    protected double getCrowFootoffset() {
        return crowFootoffset;
    }

    protected double getPerpendicularLineOffset() {
        return perpendicularLineOffset;
    }
}