###
Variable Scope. 

Variables have different "scopes". 
For example, variables declared within either the setup or draw functions 
may only be used in these functions. Class variables, variables declared with 
an @ sign before the name, may be used anywhere within the class. The @ sign is a
shortcut for "this".
Variables are localized within each block, which is easy to be recognized by
the indentation (the whitespace at the beginning of each line).
###

setup: ->
    
    @size 640, 360
    @background 0
    @stroke 255
    @noLoop()
    
    @a = 80  # Create a variable "a"

draw: ->
    # Draw a line using the global variable "a"
    @line @a, 0, @a, @height
    
    # Create a new variable "a" local to the for() statement 
    for a in [120..200] by 2
        @line a, 0, a, @height
    
    # Create a new variable "a" local to the draw() function
    a = 300
    # Draw a line using the new local variable "a"
    @line a, 0, a, @height    
    
    # Make a call to the custom function drawAnotherLine()
    @drawAnotherLine()
    
    # Make a call to the custom function setYetAnotherLine()
    @drawYetAnotherLine()

drawAnotherLine: ->
    # Create a new variable "a" local to this method
    a = 320
    # Draw a line using the local variable "a"
    @line a, 0, a, @height

drawYetAnotherLine: ->
    # Because no new local variable "a" is set, 
    # this lines draws using the original global
    # variable "a" which is set to the value 20.
    @line @a+2, 0, @a+2, @height

