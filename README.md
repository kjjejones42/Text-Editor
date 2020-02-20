# Work on project. Stage 4/4: Searching

## Description

In this stage, you will improve the usability of your text editor.

The first thing to point out is that the "Save" and "Load" buttons take up a lot of space on the screen. You can't add a lot of buttons to the top bar when they contain text. This problem can be solved using icons instead of text.

* The constructor of JButton can take an ImageIcon instead of text.
* You should replace the buttons "Load" and "Save" with corresponding icons. You can download the icons from the internet.

It is also not useful to allow users to load a file from only one directory — the project folder. The Swing library has a useful component for navigating your filesystem - JFileChooser.

* So, when the user clicks on the "Open" button (is was the button that previously named "Load") you should open a file manager using JFileChooser and let the user choose the file he wants to open.
* Then, the contents of this file should be visible in the text editor. You can use [this link](http://www.mkyong.com/swing/java-swing-jfilechooser-example/) to see how to use JFileChooser.

Also, you should add a search panel.

* It should contain a text field, a button "Start search", a button "Previous match" and a button "Next match.”
* All of these buttons should also be icons.
* The search can be by regular expressions or by plain text.
* For this, you should add a checkbox that is checked when the user wants to search using a regular expression. You can use JCheckBox for this.
* The search can slow down the GUI thread, so you should implement the search in a separate thread.

After the user presses the "Start search" button, the program should select the first part of the text that is matched and set the caret to the end of the selected part.

Use buttons "Next match" and "Previous match" to iterate through all matches in the text. 

You can use the following working code to set the caret position and select the text of the text area:

```java
textArea.setCaretPosition(index + foundText.length());
textArea.select(index, index + foundText.length());
textArea.grabFocus();
```

Do not forget about the menu. You can add a new menu list with search functionality that copies all the search buttons.

Due to testing reasons, you need to set name to some components.

Set the names to these components:

* JTextArea component to "TextArea"
* Search field to "SearchField"
* Button that saves the file to "SaveButton"
* Button that opens a filemanager to "OpenButton"
* Start search button to "StartSearchButton"
* Previous match button to "PreviousMatchButton"
* Next match button to "NextMatchButton"
* Use regex checkbox to "UseRegExCheckbox"
* JFileChooser to "FileChooser"
* ScrollPane to "ScrollPane"
* File option in menu to "MenuFile"
* Search option in menu to "MenuSearch"
* Open option in menu to "MenuOpen"
* Save option in menu to "MenuSave"
* Exit option in menu to "MenuExit"
* Start search option in menu to "MenuStartSearch"
* Previous match option in menu to "MenuPreviousMatch"
* Next match option in menu to "MenuNextMatch"
* Use regex option in menu to "MenuUseRegExp"
* For the testing reasons, you should use a single instance of JFileChooser. Add this instance to the frame using add method. Hide this instance of JFileChooser if it isn't needed at the moment and show it when it needed.

Example
Below is an example of how your text editor might look.

![ ](https://ucarecdn.com/c9a09e0a-a4bf-4b96-96d6-8180dfbda308/)

![ ](https://ucarecdn.com/4fc28e91-c3e2-4ab2-a130-f589a79cc900/)

![ ](https://ucarecdn.com/91ccfb04-98e3-4cc4-9a20-6ecae77f5682/)

![ ](https://ucarecdn.com/e6a27c9a-4195-416c-96d8-c3da0d2f5ff9/)

![ ](https://ucarecdn.com/959f414f-d7c7-44be-af07-630d224a5c36/)

![ ](https://ucarecdn.com/908cdec4-2376-4c01-a8b8-e38c2cec3b33/)

![ ](https://ucarecdn.com/05bd4f5c-e22b-453a-b31c-6641e24a7d1a/)
