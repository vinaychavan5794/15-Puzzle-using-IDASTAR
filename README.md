# 15-Puzzle-using-IDASTAR

Instruction to run the code:

->Compile the java file by using command "javac FifteenPuzzle.java".After compiling run the program by using command "java FifteenPuzzle".
->Another way to run the program is to put the file in the default package in Eclipse IDE and click run.

->To add the initial state of the puzzle, enter numbers with zero being the blank tile. Press Enter key after each input number.
Example of input:
1
0
2
4
5
7
3
8
9
6
11
12
13
10
14
15

->Once the puzzle is added select one of the following options:
1.) Press 1 for solving the puzzle using Astar(Manhattan heuristic) 
2.) Press 2 for solving the puzzle using Astar(Number of Misplaced Tiles heuristic)
3.) Press 3 to Exit

->For the unsolvable states [1 2 3 4 5 6 7 8 9 10 11 12 13 15 0 14] and [1 5 4 9 8 6 7 10 11 12 15 14 0 2 3 13] the program cannot find a solution.


