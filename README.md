# TestNG Notes

### Equipo
- Julián Enrique Espinoza Valenzuela | A01254679
- Santiago Gutiérrez González | A00572499
- Alejandro Moncada Espinosa | A01638343
- Ana Camila Jimenez Mendoza | A01174422
- Jorge Ivan Sanchez Gonzalez | A01761414

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

### Priority

```java
@Test(priority = 1)
public void firstTest() {}

@Test(priority = 2)
public void secondTest() {}
```

### Enabled / Disabled

```java
@Test(enabled = false)
public void ignoredTest() {}
```

## Organización de Pruebas

### Grupos
```java
@Test(groups = {"regression"})
public void regressionTest() {}
```
Permiten ejecutar subconjuntos de pruebas desde XML.

Para correr los tests de un grupo en maven:
```bash
mvn test -Dgroups="positive-tests"
```

### Depends on
```java
@Test
public void loginTest() {}

@Test(dependsOnMethods = "loginTest")
public void fundTransferTest() {}
```

## Parametrización

### DataProvider
``` java
@DataProvider(name = "loginData")
public Object[][] data() {
    return new Object[][] {
        {"user1", "pass1"},
        {"user2", "pass2"}
    };
}

@Test(dataProvider = "loginData")
public void testLogin(String user, String pass) {}
```

### Parameters (desde XML)
```java
@Parameters({"username"})
@Test
public void testParam(String user) {}
```
En testng.xml:
```xml
<parameter name="username" value="admin"/>
```

## Manejo de Resultados y Reportes
### Resultados
- Se ven en consola: Passed / Failed / Skipped.
- Se utilizan Assert para validaciones.

### Reportes HTML
- Se generan automáticamente en test-output/: (index.html, emailable-report.html, testng-results.xml)

### Personalización con IReporter
```java
public class CustomReport implements IReporter {
    public void generateReport(...) {
        // Personaliza HTML o genera PDF
    }
}
```

### Uso de Listeners
```java
@Listeners(MyListener.class)
public class MyTests {}
```

Tipos:
- ITestListener → Métodos individuales.
- ISuiteListener → Suite completo.

## Configuración Avanzada

### testng.xml
```xml
<suite name="MySuite">
  <test name="Test1">
    <parameter name="username" value="admin"/>
    <classes>
      <class name="com.tests.MyTestClass"/>
    </classes>
  </test>
</suite>
```
Puedes definir múltiples tests, grupos, parámetros, etc.

### Excepciones Esperadas
```java
@Test(expectedExceptions = IllegalArgumentException.class)
public void testInvalidInput() {
    throw new IllegalArgumentException();
}
```