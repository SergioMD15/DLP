# DLP

## Ampliations:
- Increment
- Decrement
- Arithmetical assignment { +=, -=, *=, /= }
- For statement
- Do while
- Multiple assignment
- Ternary operator
- Switch Case

## To do:
1) **Allow structs and arrays to be passed as parameters**
2) **Parameter passing with ref keyword.**
3) **Potential operator.**?
4) **Multiple comparison (<< or >>)**
5) **Allow variable assignment on declaration**

1. The arrays have to share the same type and have the same size as the referenced one and the struct has to have the same fields as the referenced one.
2. I need a new class (Reference) that implements Expression and also stores an Expression which has to be lvalue (be careful with indexing). It cannot be called outside of an invocation. I have to change the parser also to allow the passed parameters to be instances of Reference class.
3. Is an arithmetic operation, with an operator and two expressions. Have to be careful with the right expression because we need its value and if we have an invocation or something like that we will have to get the value. The idea is to use a loop to multiply the left expression by itself as many times as the value of the right expression. We have to evaluate the right expression just once and duplicate it on each iteration (because we need one as a reference and another one to check the logical operation). ? Waiting for confirmation.
4. Allows the comparison of multiple expressions without needing to use the logical operators. Whenever a comparison is false it stops checking.
