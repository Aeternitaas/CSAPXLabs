import turtle

def crosses(s: int, n: int) -> None:
    turtle.forward(s)
    if n-1 != 0:
        turtle.right(90)
        crosses(s/2,n-1)
        turtle.left(90)
        crosses(s/2,n-1)
        turtle.left(90)
        crosses(s/2,n-1)
        turtle.right(90)
    turtle.backward(s)

n = input(print("Give a number of iterations"))
crosses(100,int(n))
turtle.mainloop()
