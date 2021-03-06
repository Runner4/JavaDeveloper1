package ru.progwards.java1.lessons.sets;

import java.util.*;

public class ImmutableFigures {
    List<Figure> list = new ArrayList<>();
    public Figure maxFigure() {
        return Collections.max(list);
    }

    public Figure minFigure() {
        return Collections.min(list);
    }

    // вложенные классы-фигуры
    public abstract static class Figure implements Comparable<Figure> {
        @Override
        public int compareTo(Figure figure) {
            return Double.compare(this.area(), figure.area());
        }

        // периметр фигуры
        public abstract double perimeter();

        // площадь фигуры
        public double area() {
            return 0d;
        }

        public void printInfo() {
            System.out.println(this);
            System.out.println("    периметр " + perimeter());
            System.out.println("    площадь  " + area());
            System.out.println("");
        }
    }

    public static class Segment extends Figure {
        final double a;

        public Segment(double a) {
            this.a = a;
        }

        @Override
        public double perimeter() {
            return a;
        }

        @Override
        public String toString() {
            return "Отрезок, длина " + a;
        }
    }

    public static class Square extends Segment {
        public Square(double a) {
            super(a);
        }

        @Override
        public double area() {
            return a * a;
        }

        @Override
        public double perimeter() {
            return 4 * a;
        }

        @Override
        public String toString() {
            return "Квадрат " + a + " x " + a;
        }
    }

    public static class Rectangle extends Square {
        final double b;

        public Rectangle(double a, double b) {
            super(a);
            this.b = b;
        }

        @Override
        public double area() {
            return a * b;
        }

        @Override
        public double perimeter() {
            return 2 * (a + b);
        }

        @Override
        public String toString() {
            return "Прямоугольник " + a + " x " + b;
        }
    }

    public static class Circle extends Figure {
        final double radius;

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Circle circle = (Circle) o;
            return Double.compare(circle.radius, radius) == 0;
        }

        @Override
        public int hashCode() {
            return Objects.hash(radius);
        }

        public Circle(double radius) {
            this.radius = radius;
        }

        @Override
        public double area() {
            return Math.PI * radius * radius;
        }

        @Override
        public double perimeter() {
            return 2 * Math.PI * radius;
        }

        @Override
        public String toString() {
            return "Окружность с радиусом " + radius;
        }
    }

    public static class Triangle extends Segment {
        final double b;
        final double c;

        public Triangle(double a, double b, double c) {
            super(a);
            this.b = b;
            this.c = c;
        }

        @Override
        public double area() {
            // считаем по формуле Герона
            double hP = perimeter() / 2;
            return Math.sqrt(hP * (hP - a) * (hP - b) * (hP - c));
        }

        @Override
        public double perimeter() {
            return a + b + c;
        }

        @Override
        public String toString() {
            return "Треугольник со сторонами " + a + ", " + b + ", " + c;
        }
    }

    // статические методы
    public static void main(String[] args) {
        List<Figure> list = new ArrayList<>();
        list.add(new Segment(5));
        list.add(new Square(5));
        list.add(new Rectangle(5, 11));
        list.add(new Circle(5));
        list.add(new Triangle(3, 4, 5));

        List<Figure> unmodifiableList = Collections.unmodifiableList(list);

        list.sort(null);

        for (Figure figure: unmodifiableList) {
            figure.printInfo();
        }
    }
}
