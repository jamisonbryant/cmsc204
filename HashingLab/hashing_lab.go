package main

import (
  "fmt"
  "math"
)

func main() {
  // Create node arrays
  a := make([]int, 0);
  e := make([]int, 0);
  a = append(a, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
  e = append(e, 66, 47, 87, 90, 126, 150, 145, 153, 177, 285, 393, 467, 566, 620, 735);

  // Declare variables
  var i = 0;    // Array position
  var p = 0;    // Current key
  var q = 0;    // ???
  var o = 0;    // Offset
  var c = 0;    // Number of collisions
  var k = 23;   // 4k+3 prime
  var N = 19;   // Size of list

  // Assign keys to array
  for z := 0; z < len(e); z++ {
    // Get current key
    p = e[z];

    // Get array position
    i = int(math.Mod(float64(p), float64(N)));

    // Get ???
    q = p / N;

    // Get offset
    if int(math.Mod(float64(q), float64(N))) != 0 {
      o = q;
    } else {
      o = k;
    }

    // Check for collision
    for a[i] != -1 {
      // Increment collision count
      c++;

      // Recalculate position
      i = int(math.Mod(float64(i + o), float64(N)));
    }

    // Set array element
    a[i] = p;
  }

  // Print out array
  fmt.Println(a);
}
