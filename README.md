# Akari

Recreation of the classic Akari Light up game using Java, JavaFX, Maven, and the Model View Controller Architecture.

<img width="400" height="400" alt="Screenshot 2024-11-11 at 5 12 28 PM" src="https://github.com/user-attachments/assets/bbaa2b7d-afbc-4b76-b156-702aca2a5228">
<img width="400" height="400" alt="Screenshot 2024-11-11 at 5 11 11 PM" src="https://github.com/user-attachments/assets/fff09df4-095b-434b-8653-3a3b345201f1">

## 1) Objective:
- Place light bulbs in the grid to illuminate every white cell without causing any light bulbs to overlap or violate the rules.
- A cell is "illuminated" if it is in the same row or column as a light bulb without any black cells in between.

## 2) Understanding the Grid:
- White cells: These can be illuminated by light bulbs.
- Black cells: These act as barriers and may also have numbers.
- Numbered black cells: Indicate the exact number of light bulbs that should be adjacent to them (horizontally and vertically).

## 3) Placing Light Bulbs:
- Click on a white cell to place a light bulb.
- Click again on the light bulb to remove it if needed.

## 4) Rules:
- All white cells must be illuminated (no cell should remain dark).
- Numbered black cells must have the specified number of adjacent light bulbs.
- No two light bulbs should illuminate each other directly (no bulbs should be in line with each other horizontally or vertically without a black cell in between).
