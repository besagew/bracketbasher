So these brackets can be represented as binary trees pretty much, where each node's child is:
A copy of itself
The choice that lost to the node.
For example:
```
					cliff
			cliff           moss
	  cliff         moss             rhubarb
chowder  cliff	moss	lichen	rubarb      calx
```
This graph sucks. But you get the idea
## Input
For the group project, the program should take a CSV input like this:
`rhubarb, lichen, moss, cliff, chowder`
and construct the bottom row of a binary tree by just filling out the last half of an array. The array should be twice the length of the input. In this case it's 5 elements so make an array of 10

|     |                 |
| --- | --------------- |
| 0   |                 |
| 1   |                 |
| 2   |                 |
| 3   |                 |
| 4   |                 |
| 5   | rhubarb         |
| 6   | lichen          |
| 7   | moss            |
| 8   | color{red}cliff |
| 9   | chowder         |
## Interactable part
Once the bottom of the binary tree is constructed, the program should reverse iterate through the array in pairs, asking the user which of the pairs wins. The winning choice should be duplicated and put at $(x-1)/2$, the parent index.

|     |                          |
| --- | ------------------------ |
| 0   |                          |
| 1   |                          |
| 2   |                          |
| 3   |                          |
| 4   | $\color{green}cliff$     |
| 5   | rhubarb                  |
| 6   | lichen                   |
| 7   | moss                     |
| 8   | $\color{red}cliff$       |
| 9   | $\color{darkred}chowder$ |
For example, i like Cliff more than Chowder so cliff goes at (9-1)/2 = 4

During this process if the input is odd there's going to be an element at ceil(n/2)  with nothing to compare it to. If this happens, just move it to (x-1)/2

After iterating from n (9) to ciel(9/2) (5), the array should look like this.

|     |                        |
| --- | ---------------------- |
| 0   |                        |
| 1   |                        |
| 2   | $\color{green}rhubarb$ |
| 3   | $\color{green}moss$    |
| 4   | $\color{green}cliff$   |
| 5   | rhubarb                |
| 6   | lichen                 |
| 7   | moss                   |
| 8   | cliff                  |
| 9   | chowder                |
Now, compare again from floor(n/2) (4) to ciel(4/2) (2)
There is probably a less confusing way to do this. Blanking on it rn though

Doing the loop again (including moving the odd element up) gets us this:

|     |                        |
| --- | ---------------------- |
| 0   |                        |
| 1   | $\color{green}rhubarb$ |
| 2   | $\color{green}cliff$   |
| 3   | moss                   |
| 4   | cliff                  |
| 5   | rhubarb                |
| 6   | lichen                 |
| 7   | moss                   |
| 8   | cliff                  |
| 9   | chowder                |
Now its even so we can finally rank rhubarb by going from floor((n/2)/2) (2) to ceil(2/2) (1)

|     |         |
| --- | ------- |
| 0   | rhubarb |
| 1   | rhubarb |
| 2   | cliff   |
| 3   | moss    |
| 4   | cliff   |
| 5   | rhubarb |
| 6   | lichen  |
| 7   | moss    |
| 8   | cliff   |
| 9   | chowder |
and done. This is the array representing the bracket

## Displaying Data
we can either do this with javafx or just command line if that is too hard/annoying

```
N/A-----┐
		rhubarb---┐
rhubarb-┘         |
				rhubarb----┐
lichen----┐                |
		  | 			rhubarb
		moss------┐        |
moss------┘       |        |
				cliff------┘
cliff-----┐       |
		cliff-----┘
chowder---┘
```
notably, if the size of a layer is not an exponent of two (2,4,8,16,32, etc) you'll end up with an annoying value hanging on. Idk how to handle that yet

> With an input size of n, there will be floor(log_2(n))  "levels" on the bracket, where level 0 is every input, and level $\lfloor{}log_2(n)\rfloor$ only contains the winner
> Then, given an index x on the array,  the item at index x is on level (log_2(x+1))