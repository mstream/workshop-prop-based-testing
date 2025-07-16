# Adder

An implementation of arithmetic addition operation.

## Challenges of example-base approach

- high possibility of false-negatives

## Test strategy evolution

1. Example-based testing
2. Implementation mirrors test cases
3. Testing commutativity property
4. Implementation uses multiplication
5. Testing distributive property
6. Implementation uses subtraction
7. Testing identity of 1 property

## Binary arithmetic operations properties

|       property |                commutative                |                             associative                             |                               distributive                               |                        identity                         |                         
|---------------:|:-----------------------------------------:|:-------------------------------------------------------------------:|:------------------------------------------------------------------------:|:-------------------------------------------------------:|
|                | $\forall_{a,b} a \otimes b = b \otimes a$ | $\forall_{a,b,c} a \otimes (b \otimes c) = (a \otimes c) \otimes c$ | $\forall_{a,b,c} a \times (b \otimes c) = a \times b \otimes b \times c$ | $\forall_{a} \exists_{b} a \otimes b = b \otimes a = a$ |          
|       addition |              $a + b = b + a$              |                     $a + (b + c) = (a + b) + c$                     |               $a \times (b + c) = a \times b + b \times c$               |                   $a + 0 = 0 + a = a$                   |
| multiplication |         $a \times b = b \times a$         |           $a \times (b \times c) = (a \times b) \times c$           |                                   ---                                    |              $a \times 1 = 1 \times a = a$              |
|         modulo |                    ---                    |                                 ---                                 |                                   ---                                    |                           ---                           |
|    subtraction |                    ---                    |                                 ---                                 |               $a \times (b - c) = a \times b - b \times c$               |                           ---                           |
|       division |                    ---                    |                                 ---                                 |                                   ---                                    |                           ---                           |
| exponentiation |                    ---                    |                                 ---                                 |                                   ---                                    |                           ---                           |

