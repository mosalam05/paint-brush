# 🎨 Paint Brush Application
<div align="center">

  ![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=java&logoColor=white)
  ![NetBeans](https://img.shields.io/badge/NetBeans-1B6AC6?style=for-the-badge&logo=apachenetbeanside&logoColor=white)
  ![License: MIT](https://img.shields.io/badge/License-MIT-green.svg?style=for-the-badge)
  ![Platform](https://img.shields.io/badge/Platform-Windows%20%7C%20Linux-lightgrey?style=for-the-badge)

</div>


**Paint Brush** is a **Java Swing-based drawing application** that enables users to create and edit drawings with ease. It supports basic shapes, freehand sketches, color selection, erasing, and clearing. Additionally, it provides useful features such as undo/redo, and the ability to save and open drawings as image files.


## 🎯 Features
- 🖌️ **Drawing Tools**:  
  - Free Hand  
  - Rectangle (filled or outline)  
  - Oval (filled or outline)  
  - Line  
  - Eraser  

- 🎨 **Color Picker** – choose any color for your drawings.  
- ↩️ **Undo / Redo** functionality.  
- 🗑️ **Clear All** option with confirmation.  
- 💾 **Save & Open** drawings as image files (`.png`).  
- 🖼️ Ability to load images into the canvas.  


## ⬇️ Installation & Usage

1. Clone the repository:
   ```bash
   git clone https://github.com/mosalam05/paint-brush.git
   ```
2. Open the project in NetBeans, IntelliJ IDEA, or any Java IDE.
3. Run the `PaintBrush.java` file to launch the application.


## 📂 Project Structure
```
src/
├── PaintBrush.java         # Main application class (sets up JFrame, toolbar, canvas)
├── ToolBarPanel.java       # Toolbar with tools, color picker, and fill checkbox
├── DrawPanel.java          # Canvas logic for drawing, mouse events, and save/open
├── Shape.java              # Abstract Shape class and subclasses:
│   ├── RectShape.java      # Rectangle drawing logic
│   ├── OvalShape.java      # Oval drawing logic
│   ├── LineShape.java      # Line drawing logic
│   ├── FreeHandShape.java  # Freehand drawing logic
│   └── ImageShape.java     # Image loading logic
└── paintbrush/icons/       # PNG icons for toolbar buttons
```

## ⚠️ Notes

- **Icon Paths**: Icons are expected in `src/paintbrush/icons/`. If you move the project, **update paths** in `PaintBrush.java` and `ToolBarPanel.java`.
- **Image Loading**: Opening an image replaces the current canvas content. **Save your work** before loading a new image.


## ➕ Future Improvements
- ✍️ **Text Tool** – add ability to insert and edit text.  
- 🗂️ **Multiple Layers** – support working with layers for advanced editing.  
- ⌨️ **Keyboard Shortcuts** – quick access to common tools and actions.  
- 🧽 **Smart Eraser** – intelligent erasing with better precision.  


<div align="center">

  ## 👥 Contributors

  [**Mostafa Mosalam**](https://github.com/mosalam05)  
  [**Mohamed Hamed**](https://github.com/Modex0)  

</div>