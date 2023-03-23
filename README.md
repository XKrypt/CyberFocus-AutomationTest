## Teste de automação Selenium Java

##### IDE: [InteliJ](https://www.jetbrains.com/pt-br/idea/)
##### Versão do java :  17
##### Tipo de projeto :  Maven
##### Testado usando o SO : [Manjaro 22 KDE](https://manjaro.org/download/)
##### Site utilizado para a realização dos testes: [Automation Testing](https://demo.automationtesting.in)
----------------------------
# Cenários:

### Página de formulario:
```java 
formScenario(WebDriver driver)
```

### Página de frame:
```java 
frameScenario(WebDriver driver)
```

### Página de Datas:
```java 
datePickerScenario(WebDriver driver)
```
### Página de Slider:
```java 
sliderScenario(WebDriver driver)
```

--------------------------------
### Funções Auxiliares


### waitFor
```java
waitFor(int time, WebDriver driver)
```
Espera por ```time``` milésimos de segundo

### waitPageLoad
```java
waitPageLoad(WebDriver driver)
```
Espera a página carregar