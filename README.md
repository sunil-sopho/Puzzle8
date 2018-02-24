Goal: The goal of this assignment is to get some practice with shortest path algorithms.


Problem Statement: Most of you may have played an 8-puzzle game in childhood. The game consists of a 3x3
grid with 8 tiles numbered 1 to 8. There is one gap in the puzzle that allows movement of tiles. Tiles can move
horizontally or vertically. Here may be one starting configuration of an 8-puzzle:

| 1 | 2 | 3 |
 -----------
| 4 | 6 | 8 |
 -----------
| 7 | 5 | - |
 -----------

We will use the word “state” to refer to each configuration of the puzzle. We can call the configuration above as the
“start state”. Typically, our goal is to reach this configuration (goal state)

| 1 | 2 | 3 |
 -----------
| 4 | 5 | 6 |
 -----------
| 7 | 8 | - |
 -----------


One possible solution to reach the target in this example is by moving 8 down, 6 right, 5 up and then 8 left. The task
in this assignment is to find this shortest path using Djikstra’s algorithm.
To make a twist in the assignment, we will play a cost-version of the game. Here you will be given a cost function
described by 8 integers, d 1 ... d 8 , such that moving the tile numbered i will cost d i units. If d 1 = 1 and d 2 = 7, then you
can move 1 seven times to cost equal to moving 2 once. You will be given several test cases, and each test case will
consist of a start state, a goal state, and a cost function. Your task is to find the cheapest path to the goal. If multiple
paths exist with the same minimum cost, print the path with fewest moves. If multiple optimal paths to the goal exist
which take the fewest moves, then you can print any of them.
We can easily represent an 8-Puzzle configuration using a 9-character string if we call the GAP G. For example, the first
configuration above will look like 12346875G.
