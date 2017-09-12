The mean squared distance of RandomWalkers is directly propotional to N and grows linearly as a function of N(no of steps).
Infact, we can observe from the following experimental data that mean squared distance is almost equal to N. (considering T = 100000)

> java RandomWalkers 100 10000
mean squared distance = 99.7546

> java RandomWalkers 200 10000
mean squared distance = 200.092

> java RandomWalkers 500 100000
mean squared distance = 498.34092

> java RandomWalkers 1000 100000
mean squared distance = 998.98674

