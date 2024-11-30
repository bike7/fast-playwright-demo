# Project Documentation

## Overview

This document provides an overview of the `TestFixtures` and `PageOptions` classes used in the project. It explains the
purpose and usage of the methods within these classes, including parameters and comments.

## Class: `TestFixtures`

### Description

The `TestFixtures` class is a base class that provides setup and teardown methods for Playwright tests. These methods
ensure that the Playwright environment and browser are properly initialized and cleaned up after each test.

### Methods

#### `setupEnvironment`

- **Description**: Initializes the Playwright environment and launches the browser.
- **Usage**: This method is annotated with `@BeforeAll` and is executed once before all tests in the class.
- **Parameters**: None
- **Playwright Methods Used**:
    - `Playwright.create()`
    - `BrowserType.launch()`

**Example Usage**:

```java
@BeforeAll
public static void setupEnvironment(){
        playwright=Playwright.create();
        browser=playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false).setSlowMo(1000));
        }
```

#### `setupBrowser`

- **Description**: Creates a new browser context and page for each test.
- **Usage**: This method is annotated with `@BeforeEach` and is executed before each test method.
- **Parameters**: None
- **Playwright Methods Used**:
    - `Browser.newContext()`
    - `BrowserContext.newPage()`

**Example Usage**:

```java
@BeforeEach
public static void setupBrowser(){
        BrowserContext context=browser.newContext();
        page=context.newPage();
        }
```

#### `closePage`

- **Description**: Closes the current page after each test.
- **Usage**: This method is annotated with `@AfterEach` and is executed after each test method.
- **Parameters**: None
- **Playwright Methods Used**:
    - `Page.close()`

**Example Usage**:

```java
@AfterEach
public void closePage(){
        if(page!=null){
        page.close();
        }
        }
```

#### `tearDownEnvironment`

- **Description**: Closes the browser and Playwright environment.
- **Usage**: This method is annotated with `@AfterAll` and is executed once after all tests in the class.
- **Parameters**: None
- **Playwright Methods Used**:
    - `Browser.close()`
    - `Playwright.close()`

**Example Usage**:

```java
@AfterAll
public static void tearDownEnvironment(){
        if(browser!=null){
        browser.close();
        }
        if(playwright!=null){
        playwright.close();
        }
        }
```

## Class: `PageOptions`

### Description

The `PageOptions` class contains tests that demonstrate various page navigation methods using the Playwright library.

### Methods and Parameters

#### `page.navigate`

- **Description**: Navigates to a specified URL.
- **Parameters**:
    - `url` (String): The URL to navigate to.
    - `options` (Page.NavigateOptions): Options for navigation.
        - `setTimeout` (int): Maximum navigation time in milliseconds. Default is 30000 (30 seconds).
        - `setWaitUntil` (WaitUntilState): When to consider navigation succeeded.
            - `DOMCONTENTLOADED`: Consider navigation to be finished when the DOMContentLoaded event is fired.
            - `LOAD`: Consider navigation to be finished when the load event is fired. (Default)
            - `NETWORKIDLE`: Consider navigation to be finished when there are no network connections for at least 500
              ms.
        - `setReferer` (String): Sets the referer header for the navigation request. Default is null.

**Example Usage**:

```java
page.navigate("https://www.wikipedia.org",new Page.NavigateOptions().setTimeout(10000));
        page.navigate("https://www.wikipedia.org",new Page.NavigateOptions().setWaitUntil(WaitUntilState.DOMCONTENTLOADED));
        page.navigate("https://www.wikipedia.org",new Page.NavigateOptions().setWaitUntil(WaitUntilState.LOAD));
        page.navigate("https://www.wikipedia.org",new Page.NavigateOptions().setWaitUntil(WaitUntilState.NETWORKIDLE));
        page.navigate("https://www.wikipedia.org",new Page.NavigateOptions().setReferer("https://www.google.com"));
```

#### `page.reload`

- **Description**: Reloads the current page.
- **Parameters**:
    - `options` (Page.ReloadOptions): Options for reloading.
        - `setWaitUntil` (WaitUntilState): When to consider reloading succeeded.
        - `setTimeout` (int): Maximum reload time in milliseconds.

**Example Usage**:

```java
page.reload(new Page.ReloadOptions().setWaitUntil(WaitUntilState.LOAD));
```

#### `page.goBack`

- **Description**: Navigates back to the previous page in history.
- **Parameters**:
    - `options` (Page.GoBackOptions): Options for going back.
        - `setWaitUntil` (WaitUntilState): When to consider going back succeeded.
        - `setTimeout` (int): Maximum go back time in milliseconds.

**Example Usage**:

```java
page.goBack(new Page.GoBackOptions().setTimeout(60000));
```

#### `page.goForward`

- **Description**: Navigates forward to the next page in history.
- **Parameters**:
    - `options` (Page.GoForwardOptions): Options for going forward.
        - `setWaitUntil` (WaitUntilState): When to consider going forward succeeded.
        - `setTimeout` (int): Maximum go forward time in milliseconds.

**Example Usage**:

```java
page.goForward(new Page.GoForwardOptions().setWaitUntil(WaitUntilState.DOMCONTENTLOADED));
```

#### `page.click`

- **Description**: Clicks an element on the page.
- **Parameters**:
    - `selector` (String): The selector of the element to click.
    - `options` (Page.ClickOptions): Options for clicking.
        - `setClickCount` (int): Number of times to click. Default is 1.
        - `setButton` (MouseButton): Mouse button to use for clicking. Default is `LEFT`.
        - `setModifiers` (List<KeyboardModifier>): Keyboard modifiers to press while clicking.

**Example Usage**:

```java
page.click("",new Page.ClickOptions().setClickCount(10));
        page.click("",new Page.ClickOptions().setButton(MouseButton.RIGHT));
        page.click("",new Page.ClickOptions().setModifiers(List.of(KeyboardModifier.ALT,KeyboardModifier.SHIFT)));
```

#### `page.press`

- **Description**: Presses a key on the page.
- **Parameters**:
    - `selector` (String): The selector of the element to press the key on.
    - `key` (String): The key to press.
    - `options` (Page.PressOptions): Options for pressing the key.
        - `setDelay` (int): Delay between key presses in milliseconds.

**Example Usage**:

```java
page.press("","Delete",new Page.PressOptions().setDelay(1000));
```

#### `page.dblclick`

- **Description**: Double-clicks an element on the page.
- **Parameters**:
    - `selector` (String): The selector of the element to double-click.

**Example Usage**:

```java
page.dblclick("");
```

#### `page.check`

- **Description**: Checks a checkbox or radio button.
- **Parameters**:
    - `selector` (String): The selector of the element to check.

**Example Usage**:

```java
page.check("");
```

#### `page.uncheck`

- **Description**: Unchecks a checkbox.
- **Parameters**:
    - `selector` (String): The selector of the element to uncheck.

**Example Usage**:

```java
page.uncheck("");
```

#### `page.fill`

- **Description**: Fills an input field with text.
- **Parameters**:
    - `selector` (String): The selector of the input field.
    - `value` (String): The text to fill.
    - `options` (Page.FillOptions): Options for filling.
        - `setForce` (boolean): Whether to force the fill action. Default is false.

**Example Usage**:

```java
page.fill("","Hello");
        page.fill("","Hello",new Page.FillOptions().setForce(true));
```

#### `page.innerHTML`

- **Description**: Returns the inner HTML of an element.
- **Parameters**:
    - `selector` (String): The selector of the element.

**Example Usage**:

```java
page.innerHTML("#content");
```

#### `page.innerText`

- **Description**: Returns the inner text of an element.
- **Parameters**:
    - `selector` (String): The selector of the element.

**Example Usage**:

```java
page.innerText("#content");
```

#### `page.textContent`

- **Description**: Returns the text content of an element.
- **Parameters**:
    - `selector` (String): The selector of the element.

**Example Usage**:

```java
page.textContent("#content");
```

#### `page.getAttribute`

- **Description**: Returns the value of an attribute of an element.
- **Parameters**:
    - `selector` (String): The selector of the element.
    - `attribute` (String): The attribute to get the value of.

**Example Usage**:

```java
page.getAttribute("div img","alt");
```

#### `page.selectOption`

- **Description**: Selects an option in a dropdown.
- **Parameters**:
    - `selector` (String): The selector of the dropdown.
    - `value` (String): The value of the option to select.

**Example Usage**:

```java
page.selectOption("#dropdown","Option 1");
        page.selectOption("#dropdown","1");
```

#### `page.getByRole`

- **Description**: Returns an element by its ARIA role.
- **Parameters**:
    - `role` (AriaRole): The ARIA role of the element.
    - `options` (Page.GetByRoleOptions): Options for getting the element.
        - `setName` (String): The accessible name of the element.

**Example Usage**:

```java
page.getByRole(AriaRole.LINK,new Page.GetByRoleOptions().setName("Form Authentication")).click();
        page.getByRole(AriaRole.BUTTON,new Page.GetByRoleOptions().setName("Login")).click();
        page.getByRole(AriaRole.LINK,new Page.GetByRoleOptions().setName("Logout")).click();
        page.getByRole(AriaRole.LISTBOX).selectOption(new String[]{"ms2","ms3"});
```

### List of ARIA Roles

1. **alert**
2. **button**
3. **checkbox**
4. **dialog**
5. **grid**
6. **link**
7. **listbox**
8. **menu**
9. **progressbar**
10. **radio**
11. **slider**
12. **tab**
13. **textbox**
14. **tooltip**
15. **tree**

For a complete list of ARIA roles, refer to
the [WAI-ARIA specification](https://www.w3.org/TR/wai-aria-1.1/#role_definitions).