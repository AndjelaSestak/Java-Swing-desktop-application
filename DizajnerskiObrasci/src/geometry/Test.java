package geometry;

import java.lang.reflect.Array;
import java.util.Arrays;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Test klasa");
		System.out.println("Kreiranje objekta");
		Point p = new Point();
		System.out.println(p.getX());
		p.setX(10);
		System.out.println(p.getX());

		p.setY(20);
		System.out.println("Udaljenost je" + p.distance(15, 25));

		// Vezbe 3
		// Point p-> x=10,y=20
		p.setSelected(true);
		Point p1 = new Point();
		p1.setX(20);
		p1.setY(25);

		Line l1 = new Line();
		System.out.println(l1.getStartPoint() + " " + l1.getEndPoint());
		System.out.println(l1.isSelected());

		Rectangle r1 = new Rectangle();
		Circle c1 = new Circle();

		// 1.Inicijalizovati x koordinatu tacke p
		// na vrednost y koordinate tacke p1
		System.out.println(p.getX());
		p.setX(p1.getY());
		System.out.println(p.getX());

		// 2.Postaviti za pocetnu tacku linije l1 tacku p, a
		// za krajnju tacku linije l1 tacku p1
		l1.setStartPoint(p);
		l1.setEndPoint(p1);
		System.out.println(l1.getStartPoint().getX() + "" + l1.getEndPoint().getY());
		System.out.println(l1.getStartPoint().getX() + "" + l1.getEndPoint().getY());

		// 3.Postaviti y koordinatu krajnje tacke l1 na 23
		System.out.println(l1.getEndPoint().getY());
		l1.getEndPoint().setY(23);
		System.out.println(l1.getEndPoint().getY());

		// 4.Inicijalizovati x koordinatu pocetne tacke linije l1
		// na vrednost y koordinate krajnje tacke linije l1
		System.out.println(l1.getStartPoint().getX());
		l1.getStartPoint().setX(l1.getEndPoint().getY());
		System.out.println(l1.getStartPoint().getX());

		// 5.Postaviti x koordinatu krajnje tacke l1 na vrednost
		// duzine linije l1 umanjene za vrednost zbira x i y
		// koordinate pocetne tacke linije l1
		l1.getEndPoint().setX((int) l1.length() - (l1.getStartPoint().getX() + l1.getStartPoint().getY()));
		System.out.println(l1.getEndPoint().getX());

		// 6.Postaviti x koordinatu tacke gore levo pravougaonika
		// r1 na vrednost 10 i y koordinatu na vrednost 15
		System.out.println(r1.getUpperLeftPoint());

		r1.setUpperLeftPoint(p);
		// r1.setUpperLeftPoint(new Point(p.getX(),p.getY()));
		r1.getUpperLeftPoint().setX(10);
		r1.getUpperLeftPoint().setY(15);

		// 7.Postaviti centar kruga c1 na koordinate tacke
		// gore levo od r1
		c1.setCenter(r1.getUpperLeftPoint());

		// 8.Postaviti x koordinatu centra kruga c1 na vrednost razlike
		// povrsine pravougaonika r1 i y koordinate pocetne tacke linije l1
		r1.setHeight(10);
		r1.setWidth(20);
		c1.getCenter().setX(r1.area() - l1.getStartPoint().getY());
		System.out.println(c1.getCenter().getX());

		// Vezbe 4
		/*
		 * 1.Postaviti koordinatu x centra ranije kreiranog kruga k na vrednost zbira
		 * vrednosti poluprecnika kruga k i vrednosti koja predstavlja udaljenost
		 * pocetne i krajnje tacke prethodno kreirane linije lin (NE duzine). Sve
		 * objekte kreirati samostalno.
		 */
		Circle c2 = new Circle();
		//c2.setRadius(5);
		Line lin = new Line();
		Point t1 = new Point();
		Point t2 = new Point();
		t1.setX(3);
		t1.setY(4);
		t2.setX(5);
		t2.setY(6);
		lin.setStartPoint(t1);
		lin.setEndPoint(t2);

		c2.setCenter(t1);

		c2.getCenter().setX(c2.getRadius()
				+ (int) lin.getStartPoint().distance(lin.getEndPoint().getX(), lin.getEndPoint().getY()));

		// vezbe 4 - nastavak
		System.out.println("Vezbe 4");
		Point p4 = new Point(10, 15);
		Point p5 = new Point(15, 20, true);
		System.out.println(p4.getX());
		System.out.println(p5.isSelected());

		// za tacku poziva toString() i ispisuje koordinate tacke dok za liniju ispisuje
		// referencu (nismo jos redefinisali toString() u liniji)
		System.out.println(p4.toString());
		System.out.println(p4);
		System.out.println(l1);

		// nakon definisanja equals(...) metode
		// razlicite vrednosti i razlicite reference
		System.out.println(p4.equals(p5));
		System.out.println(p4 == p5);

		// dodela vrednosti i reference
		p4 = p5;

		// iste vrednosti i iste reference
		System.out.println(p4.equals(p5));
		System.out.println(p4 == p5);

		// iste vrednosti, ali razlicite reference zbog operatora NEW
		Point p6 = new Point(10, 15);
		Point p7 = new Point(10, 15);
		System.out.println(p6 == p7);
		System.out.println(p6.equals(p7));

		// ZADATAK - testirati konstruktore, toString() i equals(...) metode

		// VEZBE 5
		Point clickPoint = new Point(10, 15);
		System.out.println(p7.contains(clickPoint.getX(), clickPoint.getY()));

		System.out.println(lin.contains(clickPoint.getX(), clickPoint.getY()));

		Donut donut = new Donut(new Point(12, 17), 20, 10, true);
		System.out.println(donut.toString());
		System.out.println(donut instanceof Circle);
		System.out.println(donut instanceof Donut);
		System.out.println(donut instanceof Donut);

		// VEZBE 6
		System.out.println("Vezbe 6");
		Shape shape = new Point(10, 15);
		System.out.println(shape);
		// Shape shape2=new Shape(); ne moze

		// VEZBE 7
		System.out.println("Vezbe 7");
		Point movedPoint = new Point(10, 15);
		System.out.println(movedPoint);
		movedPoint.moveBy(5, 5);
		System.out.println(movedPoint);
		movedPoint.moveTo(10, 15);
		System.out.println(movedPoint);

		Circle movedCircle = new Circle(new Point(20, 30), 15);
		movedCircle.moveBy(5, 10);
		System.out.println(movedCircle);
		movedCircle.moveTo(15, 15);
		System.out.println(movedCircle);

		// Comparable
		movedPoint.compareTo(clickPoint);

		Line line1 = new Line(new Point(10, 15), new Point(20, 25));
		Line line2 = new Line(new Point(15, 20), new Point(25, 25));
		Line line3 = new Line(new Point(10, 25), new Point(30, 40));
		Line[] lines = { line1, line2, line3 };

		System.out.println("Niz linija pre sortiranja");
		for (int i = 0; i < lines.length; i++) {
			System.out.println(lines[i]);
		}

		Arrays.sort(lines);

		System.out.println("Niz linija posle sortiranja");
		for (int i = 0; i < lines.length; i++) {
			System.out.println(lines[i]);
		}

		Rectangle rectangle1 = new Rectangle(new Point(10, 15), 10, 15);
		Rectangle rectangle2 = new Rectangle(new Point(10, 15), 30, 40);
		Rectangle rectangle3 = new Rectangle(new Point(10, 15), 10, 10);
		Rectangle[] rectangles = { rectangle1, rectangle2, rectangle3 };

		System.out.println("Niz pravougaonika pre sortiranja");
		for (int i = 0; i < rectangles.length; i++) {
			System.out.println(rectangles[i]);
		}

		Arrays.sort(rectangles);

		System.out.println("Niz pravougaonika posle sortiranja");
		for (int i = 0; i < rectangles.length; i++) {
			System.out.println(rectangles[i]);
		}
		
		// Svi oblici u jednoj listi
				Shape[] shapes = { rectangle1, rectangle2, line2, line3 };
				for (int j = 0; j < shapes.length; j++) {
					shapes[j].moveBy(10, 15);
				}
	}
}
