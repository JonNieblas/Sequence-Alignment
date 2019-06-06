# Sequence-Alignment
Dynamically aligns strings x and y using spaces, with the goal of getting the 
strings to have similar compositions index-wise.

## Algorithm Explained

### The goal
The goal is achieved by assigning each string to an axis of a table, building 
base cases for each string, making comparisons for each case, then
choosing the best option for each comparison and saving it in the table. The 
bottom-right cell of the table will inform the program of the best string score,
allowing the program to retrace its steps and add spaces accordingly into each
string.

### The Point System
The point system is as follows:

>For char i of string x and char j of string y:
>1. If i matches j, the reward is 0 points.
>2. If i doesn't match j, the reward is 1 point.
>3. If i or j is paired with an underscore, the reward is 2 points.

### The Table
Starting with the top row and left-most column, the algorithm compares the upper,
left, and upper-left cells to see which has the lowest score. The upper and left cells
will have +2 added to their score before the comparison, since if they are chosen, 
a space will be added into the appropriate string. The best score will then be 
placed in the cell.

Each score has an identifier that lets the program know where the optimal 
placement of a space is.

The identifiers are:
* n: The upper-left cell is optimal. No space is added into either string,
      and the program moves to the upper-left cell when retracing.
* x: The left cell is optimal. A space is added to string y at this index, 
      and the program moves left towards the next char in string x when retracing.
* y: The upper cell is optimal. A space is added to string x at this index, 
      and the program moves up towards the next char in string y when retracing.

If string x = abcdefg and string y = abcefgs, the table built by this algorithm is
as follows:

```
   |   -   |   a   |   b   |   c   |   d   |   e   |   f   |   g
------------------------------------------------------------------
-  |  0 n  |  2 n  |  4 n  |  6 n  |  8 n  | 10 n  | 12 n  | 14 n
------------------------------------------------------------------
a  |  2 n  |  0 n  |  2 x  |  4 x  |  6 x  |  8 x  | 10 x  | 12 x
------------------------------------------------------------------
b  |  4 n  |  2 y  |  0 n  |  2 x  |  4 x  |  6 x  |  8 x  | 10 x
------------------------------------------------------------------
c  |  6 n  |  4 y  |  2 y  |  0 n  |  2 x  |  4 x  |  6 x  |  8 x
------------------------------------------------------------------
e  |  8 n  |  6 y  |  4 y  |  2 y  |  1 n  |  2 n  |  4 x  |  6 x
------------------------------------------------------------------
f  | 10 n  |  8 y  |  6 y  |  4 y  |  3 y  |  2 n  |  2 n  |  4 x
------------------------------------------------------------------
g  | 12 n  | 10 y  |  8 y  |  6 y  |  5 y  |  4 y  |  3 n  |  2 n
------------------------------------------------------------------
s  | 14 n  | 12 y  | 10 y  |  8 y  |  7 y  |  6 y  |  5 y  |  4 y
------------------------------------------------------------------
```

Where the first row of score-identifier values contains the base cases for string x,
and the first column of values contains the base cases for string y.

### The Base Cases
The base cases for each string will start with no characters, then iteratively add
+2 for each character that is included in the string. This is done 
because in the worst case scenario, we have a string compared to an emptry string,
requiring a space for every character; although this is great as a base case, as we can
only improve our score.

### Retracing The Steps
After the table has been built, the algorithm will start at the bottom-right corner,
retracing its steps based on the rules of the space's identifier.

For the example table above, the output given by the program will be as
follows:

```
Next Score: 4y Point: (7, 7)
Adding: _ to x string:
Adding: s to y string:

Next Score: 2n Point: (7, 6)
Adding: g to x string: _
Adding: g to y string: s

Next Score: 2n Point: (6, 5)
Adding: f to x string: g_
Adding: f to y string: gs

Next Score: 2n Point: (5, 4)
Adding: e to x string: fg_
Adding: e to y string: fgs

Next Score: 2x Point: (4, 3)
Adding: d to x string: efg_
Adding: _ to y string: efgs

Next Score: 0n Point: (3, 3)
Adding: c to x string: defg_
Adding: c to y string: _efgs

Next Score: 0n Point: (2, 2)
Adding: b to x string: cdefg_
Adding: b to y string: c_efgs

Next Score: 0n Point: (1, 1)
Adding: a to x string: bcdefg_
Adding: a to y string: bc_efgs

Final x: abcdefg_
Final y: abc_efgs
Best Score: 4
```

Therefore, after retracing, the best alignment of the two sequences are:

x= abcdefg_

y= abc_efgs

As one can see, the algorithm tries to align matching characters, and only uses spaces
when it's beneficial to do so, or to extend the length of a string.


