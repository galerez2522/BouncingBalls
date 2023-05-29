package Basis;

/**
 The Basis.Line class represents a line segment in the two-dimensional Cartesian coordinate system.
 Each line is defined by its two endpoints represented by Basis.Point objects.
 The class provides methods for calculating the length of the line,
 the middle point of the line, and determining if two lines intersect.
 */
public class Line {
    private Point realStart;
    private Point realEnd;
    private Point start;
    private Point end;

    /**
     * Constructs a new Basis.Line object defined by the two provided Basis.Point objects.
     * The constructor sorts the two endpoints according to their x-coordinates,
     * setting the start and end points of the line.
     *
     * @param start the first endpoint of the line
     * @param end   the second endpoint of the line
     */
    //constructor
    public Line(Point start, Point end) {
        this.realStart = start;
        this.realEnd = end;
        if (start.getX() < end.getX()) {
            this.start = start;
            this.end = end;
        } else {
            this.start = end;
            this.end = start;
        }
    }

    /**
     * Constructs a new Basis.Line object defined by the coordinates of its two endpoints.
     * The constructor creates two Basis.Point objects from the given coordinates and sorts
     * them according to their x-coordinates, setting the start and end points of the line.
     *
     * @param x1 the x-coordinate of the first endpoint of the line
     * @param y1 the y-coordinate of the first endpoint of the line
     * @param x2 the x-coordinate of the second endpoint of the line
     * @param y2 the y-coordinate of the second endpoint of the line
     */
    public Line(double x1, double y1, double x2, double y2) {
        Point start, end;
        start = new Point(x1, y1);
        end = new Point(x2, y2);
        this.realStart = start;
        this.realEnd = end;
        if (start.getX() < end.getX()) {
            this.start = start;
            this.end = end;
        } else {
            this.start = end;
            this.end = start;
        }
    }

    /**
     * return the start point of the line.
     * @return the start point of the line.
     */
    public Point getStart() {
        return this.start;
    }
    /**
     * return the start point of the line.
     * @return the start point of the line.
     */
    public Point getEnd() {
        return this.end;
    }

    /**
     * Returns the length of this line segment.
     *
     * @return the length of this line segment
     */
    // Return the length of the line
    public double length() {
        return this.start.distance(end);
    }

    /**
     * Returns the middle of this line segment.
     *
     * @return the middle of this line segment
     */
    // Returns the middle point of the line
    public Point middle() {
        return new Point(((this.start.getX() + this.end.getX()) / 2), ((this.start.getY() + this.end.getY()) / 2));
    }

    /**
     * Returns the start point of this line segment.
     *
     * @return the start point of this line segment
     */
    // Returns the start point of the line
    public Point start() {
        return this.start;
    }

    /**
     * Returns the end point of this line segment.
     *
     * @return the end point of this line segment
     */
    // Returns the end point of the line
    public Point end() {
        return this.end;
    }

    /**
     * Determines if this line segment intersects with the specified line segment.
     * Two line segments intersect if they have a point in common.
     *
     * @param other the other line segment to check for intersection
     * @return true if the two line segments intersect, false otherwise
     */
    // Returns true if the lines intersect, false otherwise
    @SuppressWarnings("checkstyle:WhitespaceAfter")
    public boolean isIntersecting(Line other) {
        if (equals(other)) {
            return true;
        }
        if (other.start.getY() == other.end.getY() && (other.end.getY() == this.end.getY()
                || other.end.getY() == this.start.getY())) {
            if (inRange(this.start.getX(), other.start.getX(), other.end.getX())
                    || inRange(this.end.getX(), other.start.getX(), other.end.getX())) {
                return true;
            }
        }
        // If we have one or more parallel line.
        if (this.start.getX() == this.end.getX() || other.start.getX() == other.end.getX()) {
            return this.ifParalLines(other);
        }
        double m1 = (this.start.getY() - this.end.getY()) / (this.start.getX() - this.end.getX());
        double m2 = (other.start.getY() - other.end.getY()) / (other.start.getX() - other.end.getX());
        double b1, b2;
        b1 = this.start.getY() - m1 * this.start.getX();
        b2 = other.start.getY() - m2 * other.start.getX();

        if (m1 == m2 && b1 == b2) {
            if (this.start.getX() <= other.end.getX() && this.end.getX() >= other.start.getX()) {
                return true;
            }
            if (other.start.getX() <= this.end.getX() && other.end.getX() >= this.start.getX()) {
                return true;
            }
            if (this.start.getX() >= other.end.getX() && this.end.getX() <= other.start.getX()) {
                return true;
            }
            if (other.start.getX() >= this.end.getX() && other.end.getX() <= this.start.getX()) {
                return true;
            }
        }
        if (m1 == m2) {
            return false;
        }

        double intersectionX, intersectionY;
        intersectionX = (b2 - b1) / (m1 - m2);
        intersectionY = m1 * intersectionX + b1;

        // Check if the intersection point is on the infinite lines or on them. if the intersection point is on them the
        // lines are really intersect.
        if (inRange(intersectionX, this.start.getX(), this.end.getX())
                && inRange(intersectionY, this.start.getY(), this.end.getY())
        && inRange(intersectionX, other.start.getX(), other.end.getX())
                && inRange(intersectionY, other.start.getY(), other.end.getY())) {
            return true;
        }
        return ((intersectionX >= this.start.getX() && intersectionX <= this.end.getX())
                || (intersectionX <= this.start.getX() && intersectionX >= this.end.getX()))
                && ((intersectionX >= other.start.getX() && intersectionX <= other.end.getX())
                || (intersectionX <= other.start.getX() && intersectionX >= other.end.getX()))
                && ((intersectionY >= this.start.getY() && intersectionY <= this.end.getY())
                || (intersectionY <= this.start.getY() && intersectionY >= this.end.getY()))
                && ((intersectionY >= other.start.getY() && intersectionY <= other.end.getY())
                || (intersectionY <= other.start.getY() && intersectionY >= other.end.getY()));
    }

    // Returns the intersection point if the lines intersect,
    // and null otherwise.

    /**
     * Returns the point of intersection between this line and the specified line, if any.
     *
     * @param other the line to intersect with.
     * @return the point of intersection between this line and the specified line, if any.
     */
    public Point intersectionWith(Line other) {
        if (!isIntersecting(other) || equals(other)) {
            return null;
        }
        // if the lines parallel.
        if (this.start.getX() == this.end.getX() || other.start.getX() == other.end.getX()) {
            return withParalLines(other);
        }
        if (other.start.getY() == other.end.getY() && (other.end.getY() == this.end.getY()
                || other.end.getY() == this.start.getY())) {
            if (inRange(this.start.getX(), other.start.getX(), other.end.getX())
                    || inRange(this.end.getX(), other.start.getX(), other.end.getX())) {
                if (other.end.getY() == this.end.getY()) {
                    return new Point(this.end.getX(), other.end.getY());
                } else {
                    return new Point(this.start.getX(), other.end.getY());
                }
            }
        }
        double m1 = (this.start.getY() - this.end.getY()) / (this.start.getX() - this.end.getX());
        double m2 = (other.start.getY() - other.end.getY()) / (other.start.getX() - other.end.getX());
        double b1, b2;
        b1 = this.start.getY() - m1 * this.start.getX();
        b2 = other.start.getY() - m2 * other.start.getX();

        // if the lines parallel to each other.
        if (m1 == m2 && b1 == b2) {
            if (inRange(this.start.getX(), other.start.getX(), other.end.getX()) && inRange(this.end.getX(),
                    other.start.getX(), other.end.getX())) {
                return null;
            }
            if (inRange(other.start.getX(), this.start.getX(), this.end.getX()) && inRange(other.end.getX(),
                    this.start.getX(), this.end.getX())) {
                return null;
            }
            if (this.start.getX() <= other.start.getX() && this.end.getX() > other.start.getX()) {
                return null;
            }
            if (other.start.getX() <= this.start.getX() && other.end.getX() > this.start.getX()) {
                return null;
            }
            if (this.start.equals(other.end)) {
                return this.start;
            }
            if (this.end.equals(other.start)) {
                return this.end;
            } else {
                return null;
            }
        }
        double intersectionX, intersectionY;
        intersectionX = (b2 - b1) / (m1 - m2);
        intersectionY = m1 * intersectionX + b1;
        return new Point(intersectionX, intersectionY);
    }

    /**
     * This method checks if a given line is equal to another line.
     *
     * @param other the line to compare to.
     * @return true if the two lines are equal, false otherwise.
     */
    public boolean equals(Line other) {
        return (this.start.equals(other.start) && this.end.equals(other.end))
                || (this.end.equals(other.start) && this.start.equals(other.end));
    }

    /**
     * This method checks if a given line is parallel to another line.
     *
     * @param other the line to compare to.
     * @return true if the two lines are parallel, false otherwise.
     */
    public boolean ifParalLines(Line other) {
        // If the first line is parallel to y line.
        if (this.start.getX() == this.end.getX() && other.start.getX() != other.end.getX()) {
            if (inRange(this.getStart().getX(), other.start.getX(), other.end.getX())) {
                double newX = this.start.getX();
                double m = (other.start.getY() - other.end.getY()) / (other.start.getX() - other.end.getX());
                double b = other.start.getY() - m * other.start.getX();
                double newY = m * newX + b;
                return inRange(newY, this.start.getY(), this.end.getY());
            }
            return false;
        }
        // If the other line is parallel to y line.
        if (other.start.getX() == other.end.getX() && this.start.getX() != this.end.getX()) {
            //if the x-coordinate between the line.
            if (inRange(other.getStart().getX(), this.start.getX(), this.end.getX())) {
                double newX = other.start.getX();
                double m = (this.start.getY() - this.end.getY()) / (this.start.getX() - this.end.getX());
                double b = this.start.getY() - m * this.start.getX();
                double newY = m * newX + b;
                return inRange(newY, other.start.getY(), other.end.getY());
            }
            return false;
        }
        // If they both parallel to y line.
        if (other.start.getX() == this.start.getX()) {
            double minY1 = Math.min(this.end.getY(), this.start.getY());
            double minY2 = Math.min(other.end.getY(), other.start.getY());
            double maxY1 = Math.max(this.end.getY(), this.start.getY());
            double maxY2 = Math.max(other.end.getY(), other.start.getY());
            return (maxY1 >= minY2 && minY1 <= minY2) || (maxY2 >= minY1 && minY2 <= minY1);
        }
        return false;
    }

    /**
     * This method finds the intersection point of two given lines, if exists.
     *
     * @param other the line to intersect with.
     * @return the point of intersection, or null if no intersection exists.
     */
    public Point withParalLines(Line other) {
        if (!this.isIntersecting(other)) {
            return null;
        }
        // If the first line is parallel to y line.
        if (this.start.getX() == this.end.getX() && other.start.getX() != other.end.getX()) {
            double m = (other.start.getY() - other.end.getY()) / (other.start.getX() - other.end.getX());
            double b = other.start.getY() - m * other.start.getX();
            double y = m * this.start.getX() + b;
            return new Point(this.start.getX(), y);
        }
        // If the other line is parallel to y line.
        if (this.start.getX() != this.end.getX() && other.start.getX() == other.end.getX()) {
            double m = (this.start.getY() - this.end.getY()) / (this.start.getX() - this.end.getX());
            double b = this.start.getY() - m * this.start.getX();
            double y = m * other.start.getX() + b;
            return new Point(other.start.getX(), y);
        }
        // if they both parallel to y line.
        double minY1 = Math.min(this.end.getY(), this.start.getY());
        double minY2 = Math.min(other.end.getY(), other.start.getY());
        double maxY1 = Math.max(this.end.getY(), this.start.getY());
        double maxY2 = Math.max(other.end.getY(), other.start.getY());

        if (compareDouble(minY1, maxY2)) {
            return new Point(this.start.getX(), minY1);
        }
        if (compareDouble(minY2, maxY1)) {
            return new Point(this.start.getX(), maxY1);
        }
        return null;
    }

    static boolean compareDouble(double x, double y) {
        double diff = Math.abs(x - y);
        return diff <= 0.0001;
    }

    /**
     * Returns the closest intersection point to the start of the line with a given rectangle,
     * or null if there is no intersection.
     * @param rect the rectangle to check for intersection points with the line
     * @return the closest intersection point to the start of the line with the given rectangle,
     * or null if there is no intersection
     */
    public Point closestIntersectionToStartOfLine(Rectangle rect) {

// Check if the line is one of the rectangle's sides
        int index = oneOnOther(rect);
        if (index != 5) {
            if (index == 1) {
// Basis.Line is on the left side of the rectangle
                Point start, end;
                start = rect.getUpperLeft();
                end = new Point(rect.getUpperLeft().getX(), rect.getUpperLeft().getY() + rect.getHeight());
                Line rib1 = new Line(start, end);
                if (this.realStart.distance(rib1.start) < this.realStart.distance(rib1.end)) {
                    return rib1.start;
                } else {
                    return rib1.end;
                }
            }
            if (index == 2) {
// Basis.Line is on the top side of the rectangle
                Point start, end;
                start = rect.getUpperLeft();
                end = new Point(rect.getUpperLeft().getX() + rect.getWidth(), rect.getUpperLeft().getY());
                Line rib2 = new Line(start, end);
                if (this.realStart.distance(rib2.start) < this.realStart.distance(rib2.end)) {
                    return rib2.start;
                } else {
                    return rib2.end;
                }
            }
            if (index == 4) {
// Basis.Line is on the bottom side of the rectangle
                Point start, end;
                start = new Point(rect.getUpperLeft().getX(), rect.getUpperLeft().getY() + rect.getHeight());
                end = new Point(rect.getUpperLeft().getX() + rect.getWidth(),
                        rect.getUpperLeft().getY() + rect.getHeight());
                Line rib4 = new Line(start, end);
                if (this.realStart.distance(rib4.start) < this.realStart.distance(rib4.end)) {
                    return rib4.start;
                } else {
                    return rib4.end;
                }
            }
            if (index == 3) {
// Basis.Line is on the right side of the rectangle
                Point start, end;
                start = new Point(rect.getUpperLeft().getX() + rect.getWidth(),
                        rect.getUpperLeft().getY() + rect.getHeight());
                end = new Point(rect.getUpperLeft().getX() + rect.getWidth(), rect.getUpperLeft().getY());
                Line rib3 = new Line(start, end);
                if (this.realStart.distance(rib3.start) < this.realStart.distance(rib3.end)) {
                    return rib3.start;
                } else {
                    return rib3.end;
                }
            }
        }

// If the line is not one of the rectangle's sides, check for intersection points
        java.util.List<Point> list = rect.intersectionPoints(this);
        double smallDistance = 9000000;
        Point closePoint = null;
        if (!list.isEmpty()) {
            for (Point p : list) {
                if (p.getY() == rect.getUpperLeft().getY()
                        || p.getY() == rect.getUpperLeft().getY() + rect.getHeight()) {
                    return p;
                }
                double dis = p.distance(this.realStart);
                if (dis < smallDistance) {
                    smallDistance = dis;
                    closePoint = p;
                }
            }
        }
        return closePoint;
    }

    /**
     * Checks if this rectangle is one on top of the given rectangle, and returns the relative position.
     * Returns 1 if this rectangle is above the given rectangle, 2 if it's to the right,
     * 3 if it's below, 4 if it's to the left, and 5 if the rectangles overlap or are not adjacent.
     *
     * @param rect the rectangle to check against
     * @return an int value representing the relative position of this rectangle to the given rectangle
     */
    public int oneOnOther(Rectangle rect) {
        Line rib1, rib2, rib3, rib4;
        Point start, end;
        start = rect.getUpperLeft();
        end = new Point(rect.getUpperLeft().getX(), rect.getUpperLeft().getY() + rect.getHeight());
        rib1 = new Line(start, end);
        start = rect.getUpperLeft();
        end = new Point(rect.getUpperLeft().getX() + rect.getWidth(), rect.getUpperLeft().getY());
        rib2 = new Line(start, end);
        start = new Point(rect.getUpperLeft().getX() + rect.getWidth(), rect.getUpperLeft().getY() + rect.getHeight());
        end = new Point(rect.getUpperLeft().getX() + rect.getWidth(), rect.getUpperLeft().getY());
        rib3 = new Line(start, end);
        start = new Point(rect.getUpperLeft().getX(), rect.getUpperLeft().getY() + rect.getHeight());
        end = new Point(rect.getUpperLeft().getX() + rect.getWidth(), rect.getUpperLeft().getY() + rect.getHeight());
        rib4 = new Line(start, end);

        if (rib2.start.getY() == this.start.getY() && rib2.start.getY() == this.end.getY()) {
            if (inRange(rib2.end.getX(), this.start.getX(), this.end.getX())) {
                return 2;
            }
        }
        if (rib4.start.getY() == this.start.getY() && rib4.start.getY() == this.end.getY()) {
            if (inRange(rib4.end.getX(), this.start.getX(), this.end.getX())) {
                return 4;
            }
        }
        if (rib1.start.getX() == this.start.getX() && rib1.start.getX() == this.end.getX()) {
            double minY1 = Math.min(this.end.getY(), this.start.getY());
            double minY2 = Math.min(rib1.end.getY(), rib1.start.getY());
            double maxY1 = Math.max(this.end.getY(), this.start.getY());
            double maxY2 = Math.max(rib1.end.getY(), rib1.start.getY());
            if ((maxY1 >= minY2 && minY1 <= minY2) || (maxY2 >= minY1 && minY2 <= minY1)) {
                return 1;
            }
        }
        if (rib3.start.getX() == this.start.getX() && rib2.start.getX() == this.end.getX()) {
            double minY1 = Math.min(this.end.getY(), this.start.getY());
            double minY2 = Math.min(rib3.end.getY(), rib3.start.getY());
            double maxY1 = Math.max(this.end.getY(), this.start.getY());
            double maxY2 = Math.max(rib3.end.getY(), rib3.start.getY());
            if ((maxY1 >= minY2 && minY1 <= minY2) || (maxY2 >= minY1 && minY2 <= minY1)) {
                return 3;
            }
        }
        return 5;

    }
    /**
     Checks if a given value is within a specified range.
     @param x the value to check
     @param y the lower bound of the range
     @param z the upper bound of the range
     @return true if the value is within the range, false otherwise
     */
    public Boolean inRange(double x, double y, double z) {
        return Math.max(y, z) >= x && Math.min(y, z) <= x;
    }
}
