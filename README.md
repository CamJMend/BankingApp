# TestNG Notes

## Introducción

- TestNG (Test Next Generation) es un framework de testing inspirado en JUnit.
- Permite realizar pruebas unitarias, de integración y funcionales.
- Soporta funcionalidades como: dependencias, agrupación, paralelismo, parámetros y reportes automáticos.

---

## Conceptos Básicos

### Anotaciones Fundamentales

- @Test → Marca un método como prueba.
- @BeforeMethod → Se ejecuta antes de cada @Test.
- @AfterMethod → Después de cada @Test.
- @BeforeClass / @AfterClass → Antes y después de todos los métodos de una clase.
- @BeforeSuite / @AfterSuite → Para configuración global antes/después del suite completo.

### Ejecución de Pruebas Simples

```java
@Test
public void simpleTest() {
    Assert.assertEquals(2 + 2, 4);
}
```