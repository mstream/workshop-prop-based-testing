# Different paths, same destination

```mermaid
flowchart LR
    a["initial"]
    b["intermediate 1"]
    c["intermediate 2"]
    d["outcome"]
    a -- action 1 --> b
    a -- action 2 --> c
    b -- action 2 --> d
    c -- action 1 --> d
```

## Examples

- commutativity of addition (orders of operands does not matter)
- associativity of addition (independence of order)
- identity of addition (some operands do not affect results)