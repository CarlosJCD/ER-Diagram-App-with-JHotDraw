package CH.ifa.draw.samples.erdiagram.tips;

import java.awt.Color;
import java.awt.Polygon;

import CH.ifa.draw.figures.AbstractLineDecoration;

public class OneTip extends AbstractLineDecoration {

    private double length;
    private double oneOffset;
    private double perpendicularLineOffset;

    public OneTip() {
        this(10, 10, 20);
    }

    public OneTip(double length, double oneOffset, double perpendicularLineOffset) {
        setLength(length);
        setOneOffset(oneOffset);
        setPerpendicularLineOffset(perpendicularLineOffset);
        setFillColor(Color.yellow);
    }

    @Override
    public Polygon outline(int x1, int y1, int x2, int y2) {
        double dir = Math.atan2(y2 - y1, x2 - x1);

        int offsetX1 = x2 - (int) (oneOffset * Math.cos(dir));
        int offsetY1 = y2 - (int) (oneOffset * Math.sin(dir));

        int offsetX2 = x2 - (int) (perpendicularLineOffset * Math.cos(dir));
        int offsetY2 = y2 - (int) (perpendicularLineOffset * Math.sin(dir));

        Polygon shape = new Polygon();

        shape.addPoint(
                offsetX1,
                offsetY1);

        double perpDir = dir + Math.PI / 2;
        shape.addPoint(
                offsetX1 + (int) (length * Math.cos(perpDir)),
                offsetY1 + (int) (length * Math.sin(perpDir)));

        shape.addPoint(
                offsetX1,
                offsetY1);

        perpDir = dir - Math.PI / 2;
        shape.addPoint(
                offsetX1 + (int) (length * Math.cos(perpDir)),
                offsetY1 + (int) (length * Math.sin(perpDir)));

        shape.addPoint(
                offsetX1,
                offsetY1);

        shape.addPoint(
                offsetX2,
                offsetY2);

        perpDir = dir - Math.PI / 2;
        shape.addPoint(
                offsetX2 + (int) (length * Math.cos(perpDir)),
                offsetY2 + (int) (length * Math.sin(perpDir)));

        shape.addPoint(
                offsetX2,
                offsetY2);

        perpDir = dir + Math.PI / 2;
        shape.addPoint(
                offsetX2 + (int) (length * Math.cos(perpDir)),
                offsetY2 + (int) (length * Math.sin(perpDir)));

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

    protected void setOneOffset(double newOffset) {
        oneOffset = newOffset;
    }

    protected void setPerpendicularLineOffset(double newOffset) {
        perpendicularLineOffset = newOffset;
    }

    protected double getOneOffset() {
        return oneOffset;
    }

    protected double getPerpendicularLineOffset() {
        return perpendicularLineOffset;
    }
}