package ru.job4j.assertj;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class BoxTest {
    @Test
    void isThisSphere() {
        Box box = new Box(0, 10);
        String name = box.whatsThis();
        assertThat(name).isEqualTo("Sphere")
                .isNotEqualTo("Unknown object")
                .isNotNull();
    }

    @Test
    void isThisUnknown() {
        Box box = new Box(6, 5);
        String name = box.whatsThis();
        assertThat(name).isEqualTo("Unknown object")
                .isNotEmpty()
                .isNotIn("Sphere", "Tetrahedron", "Cube");
    }

    @Test
    void  whenNumberOfVerticesFour() {
        Box box = new Box(4, 8);
        int result = box.getNumberOfVertices();
        assertThat(result).isEqualTo(4)
                .isPositive()
                .isNotNull()
                .isGreaterThan(0);
    }

    @Test
    void  whenNumberOfVerticesMinusFour() {
        Box box = new Box(-4, 8);
        int result = box.getNumberOfVertices();
        assertThat(result).isEqualTo(-1)
                .isNotPositive()
                .isLessThan(0)
                .isNotEqualTo(8);
    }

    @Test
    void  whenExist() {
        Box box = new Box(8, 8);
        boolean result = box.isExist();
        assertThat(result).isTrue()
                .isNotNull()
                .isNotEqualTo(8);
    }

    @Test
    void  whenNotExist() {
        Box box = new Box(-3, 8);
        boolean result = box.isExist();
        assertThat(result).isFalse()
                .isNotNull();
    }

    @Test
    void sphereGetArea() {
        Box sphere = new Box(0, 5);
        double result = sphere.getArea();
        assertThat(result).isEqualTo(314.1d, withPrecision(0.06d))
                .isNotEqualTo(0)
                .isNotNull()
                .isGreaterThan(0)
                .isLessThan(314.2d);
    }

    @Test
    void cubeGetArea() {
        Box cube = new Box(8, 4);
        double result = cube.getArea();
        assertThat(result).isEqualTo(96)
                .isNotNegative()
                .isGreaterThan(4);
    }
}