# Programación Funcional
La programación funcional es un paradigma de la programación, se está haciendo muy popular y se centra en torno a funciones puras. Las aplicaciones funcionales evitan el estado compartido y tienden a ser más concisas y predecibles que las que utilizan código orientado a objetos.

## Interfaz funcional
Una interfaz funcional solo puede llevar un método abstracto, es decir, una firma o un método sin cuerpo como el siguiente ejemplo, es por ello que colocamos la anotación correspondiente para interfaces funcionales.
````java
@FunctionalInterface
public interface EmailSender {
    void send();
}
````
Sin embargo, la interfaz funcional puede llevar métodos con cuerpo como privados, estáticos y default.

## Clases anónimas en Java
Una clase anónima en Java es una manera concisa de implementar una clase sin tener que declarar una clase completa con nombre. Se utiliza comúnmente para crear una instancia de una interfaz o una clase abstracta en el lugar donde se necesita sin tener que escribir una clase separada para esa implementación.
````java
EmailSender sender = new EmailSender() {
    @Override
    public void send() {
        System.out.println("Sending email from anonymous class");
    }
};

sender.send();
````
Como en el anterior ejemplo, implementamos una interfaz llamada `EmailSender` sin necesidad de ir a crear otra clase con nombre.
## ¿Qué son las expresiones Lambda?
Una expresión lambda es simplemente una función sin nombre. Incluso puede utilizarse como parámetro en una función. La expresión lambda facilita la programación funcional y simplifica mucho el desarrollo.

El uso principal de la expresión lambda es proporcionar una implementación para interfaces funcionales.

### Sintaxis de una expresión Lambda
`(parameters) -> expression`

`(parameters) -> { statements; }`

![Imgur](https://i.imgur.com/FMc0PIn.jpg)
# Java Streams API
La API Stream en Java es una parte esencial de Java 8 y versiones posteriores que proporciona una forma más concisa y eficiente de realizar operaciones de procesamiento de datos en colecciones (como listas, conjuntos y mapas) y arrays. Esta API permite realizar operaciones en secuencias de elementos de manera funcional y en paralelo, lo que puede llevar a un código más limpio y a un mejor rendimiento en ciertos casos.

<b>Los streams son un flujo de datos que se apoya de la programación funcional para trabajar,
los metodos como filter, map etc son operadores intermediarios, una vez tengamos lo que queremos con los operadores intermediarios, procedemos a usar los operadores terminales los cuales nos ayudarán a obtener el resultado final de todo el trabajo de los streams, estos operadores terminales pueden ser sum, o el mas tipico collect to list.</b>

## IntStream
IntStream es un método de la API Stream para iterar a partir de un rango de valores, estos pueden ser inclusivos o exclusivos dependiendo del método utilizado.

Si se usa `range()` el primer argumento es inclusivo y el segundo exclusivo.

Si se usa `rangeClosed()` ambos argumentos son inclusivos.
````java
public void range() throws Exception {
    System.out.println("Using fori");
    for (int i = 1; i <= 10; i++) {
        System.out.println(i);
    }
    
    System.out.println("Using IntStream Exclusive");
    IntStream.range(1, 10).forEach(System.out::println);
    
    System.out.println("Using IntStream Inclusive");
    IntStream.rangeClosed(1, 10).forEach(System.out::println);
}
````

## Min and Max
`min()` y `max()` son dos operadores intermedios que sirven, como su nombre indica, obtener minimos y maximos de una lista de números, se apoya de la interfaz `Comparator` para hacerlo.
````java
public void min() {
    List<Integer> numbers = List.of(1, 2, 3, 100, 23, 93, 99);
    Integer min = numbers.stream().min(Comparator.naturalOrder()).get();
    //Integer integer = numbers.stream().min(Integer::compareTo).get();
    System.out.println(min);
}

public void max() {
    List<Integer> numbers = List.of(1, 2, 3, 100, 23, 93, 99);
    Integer max = numbers.stream().max(Comparator.naturalOrder()).get();
    System.out.println(max);
}
````

## Uso de Distinct y Set
Con `distinct()` podemos eliminar valores repetidos de una lista, o bien, directamente convertir una lista a un set ya que esta estructura de datos no permite datos duplicados.
````java
public void distinct() throws Exception {
    List<Integer> numbers = List.of(1, 1, 2, 2, 3, 3, 4, 4, 5, 5, 6, 6, 7, 7, 8, 8, 9, 9, 9, 9, 9);
    List<Integer> distinct = numbers.stream().distinct().toList();
    System.out.println(distinct);
}

public void distinctWithSet() throws Exception {
    List<Integer> numbers = List.of(1, 1, 2, 2, 3, 3, 4, 4, 5, 5, 6, 6, 7, 7, 8, 8, 9, 9, 9, 9, 9);
    //Set<Integer> distinc = numbers.stream().collect(Collectors.toSet());
    Set<Integer> distinc = new HashSet<>(numbers);
    System.out.println(distinc);
}
````

## Uso de Filter
Uno de los operadores intermedios mas importantes y mas utilizados en el Java API Stream, el operador `filter` utiliza un `predicate`, que es una expresión lambda que devuelve un valor booleano, muy útil para realizar validaciones o filtrar datos.
````java
public void filter() throws Exception {
    List<Car> cars = MockData.getCars();
    cars
        .stream()
        .filter(car -> car.getPrice() < 20_000.00)
        .filter(car -> car.getColor().equals("Yellow"))
        .toList()
        .forEach(System.out::println);
}
````
En el anterior ejemplo estamos filtrando autos cuyo precio sea menor a 20k y sean color amarillos.

### findFirst vs findAny con Filter
Cuando queremos buscar un valor en concreto en una lista o arreglo de valores, luego de hacer el `filter` podemos colocar `findFirst` o bien `findAny`, en principio ambos tienen la misma función, ambos van a devolver el resultado que buscamos de acuerdo al `filter`, pero hay una sutil diferencia entre ambos.
- `findFirst`: este operador va a encontrar la PRIMERA coincidencia en la lista y retornará ese valor si es que lo encuentra.
- `findAny`: en cambio, este va a encontrar CUALQUIER valor en la lista que coincida con lo que hemos colocado en `filter`.
````java
public void findFirst() throws Exception {
    int[] numbers = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
    int result = Arrays.stream(numbers)
            .filter(n -> n == 6)
            .findFirst()
            .orElse(-1);
    System.out.println(result);
}

public void findAny() throws Exception {
    int[] numbers = {1, 2, 3, 4, 5, 6, 7, 8, 9, 9, 10};
    int result = Arrays.stream(numbers)
            .filter(n -> n == 6)
            .findAny()
            .orElse(-1);
    System.out.println(result);
}
````
En nuestro ejemplo anterior, en dado caso que el `filter` no encuentre lo que buscamos retornará un `-1`

### allMatch vs anyMatch
Operadores muy importantes y muy utilizados cuando buscamos que todos los elementos o al menos uno de los elementos de nuestra lista o arreglo cumplan con una condición.
- `allMatch`: este operador devuelve un valor booleano, devuelve `true` si TODOS los valores de la lista cumplen la condición dada.
- `findAny`: este operador igualmente devuelve un valor booleano, va a devolver `true` si AL MENOS UN valor de la lista cumple la condición dada.
````java
public void allMatch() throws Exception {
    int[] even = {2, 4, 6, 8, 10};
    boolean allMatch = Arrays.stream(even).allMatch(n -> n % 2 == 0);
    System.out.println(allMatch);
}

public void anyMatch() throws Exception {
    int[] evenAndOneOdd = {2, 4, 6, 8, 10, 11};
    boolean anyMatch = Arrays.stream(evenAndOneOdd).anyMatch(n -> !(n % 2 == 0));
    System.out.println(anyMatch);
}
````
En el anterior ejemplo usamos `anyMatch` para verificar si TODOS los elementos de la lista son números pares. En el siguiente método verificamos si hay AL MENOS UN valor impar en nuestra lista. Ambos casos devuelven `true`

### Uso de Map
Muchas veces necesitamos hacer ciertas conversiones entre datos, convertir una lista a un Optional o viceversa, aquí es donde entra el operador intermedio `map`. El operador `map` recibe una expresión lambda de tipo `Function`, recordemos que `Function` tiene dos parámetros, el primer parámetro es el dato de entrada y el segundo es el dato de salida, o en otras palabras, el primer argumento es el tipo de dato con el que trabajaremos y el segundo argumento es el tipo de dato que vamos a retornar, aunque en realidad pueden ir objetos o una lista!

Ejemplo con `map`
````java
void yourFirstTransformationWithMap() throws IOException {
    List<Person> people = MockData.getPeople();
    long count = people
            .stream()
            .filter(p -> p.getAge() > 18)
            .map(p -> new PersonDTO(p.getId(), p.getFirstName(), p.getAge()))
            .count();
    System.out.println(count);
}
````
En el anterior ejemplo convertimos una lista de personas a una personDTO con sus atributos, además de eso colocamos un `filter` para que únicamente retorne personas mayores a 18 años. Como operador terminal utilizamos `count` para que nos muestre un total de todas las personas mayores a 18 años. Y así podemos jugar con muchos operadores intermedios hasta dar con el dato final o resultado final que buscamos.

### Uso de flatMap
`flapMap` nos sirve principalmente para convertir una lista de listas a una sola lista, o en otras palabras, convertir una colección de datos que contiene un conjunto de colecciones a una sola colección. Puede sonar un poco confuso XD
Por ejemplo, tenemos `List<List<String>>` una lista dentro de otra lista, con `flatMap` vamos a convertir esto a `List<String` una simple lista de String.

Convirtiendo una lista de listas a una simple lista.
````java
public void withFlatMap() throws Exception {
    List<String> names = arrayListOfNames.stream()
            .flatMap(List::stream)
            .toList();
    names.forEach(System.out::println);
}
````

Convirtiendo una Lista de Optionals a una simple lista
````java
public void flatMapWithOptionals() {
    List<Optional<String>> optionals = List.of(
            Optional.of("Amigos"),
            Optional.of("Code")
    );
    List<String> collect = optionals
            .stream()
            .flatMap(Optional::stream)
            .toList();
    collect.forEach(System.out::println);
}
````
Si observamos los anteriores ejemplos, la clave para entender el `flatMap` es fijarnos en su interior, cuando convertimos la lista de listas a una simple lista colocamos `List::stream`, en cambio, cuando convertimos la lista de optionals colocamos `Optional::stream`. Con esto nos podemos hacer una idea de como podemos usar `flapMap` para conversiones complejas.

## Statistics with Streams
Con streams también podemos obtener muchos datos estadísticos de interés, por ejemplo, el valor mínimo, máximo, suma de valores, cantidad de valores o un promedio.
````java
public void count() throws Exception {
    List<Car> cars = MockData.getCars();
    long ford = cars
            .stream()
            .filter(c -> c.getMake().equals("Ford"))
            .filter(car -> car.getYear() > 2010)
            .count();
    System.out.println(ford);
}

public void min() throws Exception {
    List<Car> cars = MockData.getCars();
    double min = cars.stream()
            .mapToDouble(Car::getPrice)
            .min()
            .orElse(0);
    System.out.println(min);
}

public void max() throws Exception {
    List<Car> cars = MockData.getCars();
    double max = cars.stream()
            .mapToDouble(Car::getPrice)
            .max()
            .orElse(0);
    System.out.println(max);
}

public void average() throws Exception {
    List<Car> cars = MockData.getCars();
    OptionalDouble average = cars.stream()
            .mapToDouble(Car::getPrice)
            .average();
    System.out.println(average);
}

public void sum() throws Exception {
    List<Car> cars = MockData.getCars();
    double sum = cars.stream()
            .mapToDouble(Car::getPrice)
            .sum();
    System.out.println(BigDecimal.valueOf(sum));
}

public void statistics() throws Exception {
    List<Car> cars = MockData.getCars();
    DoubleSummaryStatistics statistics = cars.stream()
            .mapToDouble(Car::getPrice)
            .summaryStatistics();
    System.out.println(statistics.getMin());
    System.out.println(statistics.getMax());
    System.out.println(statistics.getAverage());
    System.out.println(statistics.getCount());
    System.out.println(BigDecimal.valueOf(statistics.getSum()));
}
````

## Agrupando datos con groupingBy
De manera similar como lo hacemos en SQL con groupingBy, usando streams también podemos agrupar datos con `groupingBy`
````java
public void simpleGrouping() throws Exception {
    List<Car> cars = MockData.getCars();
    Map<String, List<Car>> make = cars.stream()
            .collect(Collectors.groupingBy(Car::getMake));
    make.forEach((s, cars1) -> {
        System.out.println("Make: " + s);
        cars1.forEach(System.out::println);
        System.out.println("-------------------------");
    });
}
````
En el anterior ejemplo estamos agrupando autos por fabricante, si observamos obtenemos un `map` que cuya llave es el nombre del fabricante y el valor es o son un listado de los autos pertenecientes a dicho fabricante.
````java
public void groupingAndCounting() throws Exception {
    List<String> names = List.of(
            "John",
            "John",
            "Mariam",
            "Alex",
            "Mohammado",
            "Mohammado",
            "Vincent",
            "Alex",
            "Alex"
    );
    Map<String, Long> map = names.stream()
            .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
    System.out.println(map);
}
````
El anterior ejemplo es un poco diferente, con `Function.identity()` como primer parámetro y con `Collectors.counting()` como segundo parámetro logramos obtener la cantidad de nombres que se repiten en nuestra lista.

## Sorting con Streams
Una de las grandes ventajas que nos ofrecen los Streams es que podemos hacer sorting de manera sumamente sencilla por medio del operador `sorted`
````java
public void sortingSteamOfElements() throws IOException {
    List<Person> people = MockData.getPeople();
    people.stream()
            .map(Person::getFirstName)
            .sorted()
            .toList()
            .forEach(System.out::println);
}

public void sortingSteamOfElementsReverse() throws IOException {
    List<Person> people = MockData.getPeople();
    people.stream()
            .map(Person::getFirstName)
            .sorted(Comparator.reverseOrder())
            .toList()
            .forEach(System.out::println);
}
````
En el anterior ejemplo, primer método, utilizamos `map` para obtener un `Stream<String>` que contiene el primer nombre de las personas, luego con `sorted()` los ordenamos de manera ascendente.
En el segundo método observemos que dentro de `sorted()` colocamos `Comparatos.reverseOrder()` para que el orden sea invertido.
````java
public void sortingSteamOfObjets() throws IOException {
    List<Person> people = MockData.getPeople();
    people.stream()
            .sorted(Comparator.comparing(Person::getFirstName))
            .toList()
            .forEach(System.out::println);
}

public void topTenMostExpensiveBlueCars() throws IOException {
    List<Car> cars = MockData.getCars();
    cars.stream()
            .filter(car -> car.getColor().equalsIgnoreCase("Blue"))
            .sorted(Comparator.comparing(Car::getPrice).reversed())
            .limit(10)
            .toList()
            .forEach(System.out::println);
}
````
También podemos ordenar objetos enteros como vemos en el anterior ejemplo, esta vez no estamos mapeando absolutamente nada, creamos un stream y luego usamos `sorted()` pero con la diferencia que ahora dentro colocamos `Comparator.comparing(Person::getFirstName)` con esto hacemos que el listado de objetos se ordene de forma ascendente por nombre.
En el segundo ejemplo estamos obteniendo los diez autos azules mas caros.