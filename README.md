# Enhanced 2D Graphics Desktop Application with Design Patterns

University project for the course **Design Patterns (Dizajnerski obrasci)** (2023/2024) – Faculty of Technical Sciences, University of Novi Sad.

Extended Java Swing desktop application for 2D graphics editing, building on the OOIT practical exam. Implemented advanced features including multi-color drawing, transparency, MVC architecture, multiple design patterns, undo/redo, logging, serialization, Z-axis manipulation, multiple selection, and dynamic UI updates.

## Project Overview
The application supports drawing, selecting, modifying, and deleting 2D shapes with a strong emphasis on design patterns, clean architecture, and user interaction. All class, method, and variable names are in English. The system is realized strictly according to the MVC architectural pattern.

## Key Features Implemented

1. **Multi-color drawing**  
   Shapes can be drawn with different edge and fill colors using JColorChooser.

2. **Transparency for Donut**  
   Inner part of Donut (circle with hole) is transparent using java.awt.Graphics2D, java.awt.Shape, java.awt.geom.Area, and java.awt.geom.Ellipse2D.

3. **Hexagon support**  
   Adding, deleting, and modifying Hexagon shapes using Adapter pattern with hexagon.jar.

4. **Undo/Redo functionality**  
   Undo executed commands and redo undone commands using Command and Prototype patterns. Undo/redo buttons are available only when functionality is possible.

5. **Action logging**  
   Generate and display log of executed commands within the application.

6. **File save/load with Strategy pattern**  
   - Save log to text file  
   - Save/load entire drawing using Serialization  
   - Load log step by step (command by command in user interaction)

7. **Z-axis manipulation**  
   - To Front (position by position)  
   - To Back (position by position)  
   - Bring To Front (to highest position)  
   - Bring To Back (to lowest position)

8. **Active colors display**  
   Show current active colors for edge and fill; click to open dialog for changing active color.

9. **Multiple shape selection**  
   Enable selection of multiple shapes.

10. **Dynamic delete button**  
    Delete button available only when there are selected objects – Observer pattern.

## Technologies & Tools

- **Java Swing** – GUI framework (JPanel, JDialog, JColorChooser, etc.)
- **MVC Architecture** – Model (shapes), View (PnlDrawing), Controller
- **Design Patterns**:
  - Adapter (for Hexagon integration)
  - Command & Prototype (undo/redo stack)
  - Strategy (save/load operations)
  - Observer (dynamic UI updates, e.g., delete button)
- **Graphics2D & AWT Geometry** – for transparency, shapes, and rendering
- **Serialization** – save/load complete drawing
- **File I/O** – logging to text file
- **GitHub** – version control
