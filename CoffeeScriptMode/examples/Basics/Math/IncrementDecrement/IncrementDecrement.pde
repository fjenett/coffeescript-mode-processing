###
Increment Decrement. 

Writing "a++" is equivalent to "a = a + 1".    
Writing "a--" is equivalent to "a = a - 1".     
###

setup: ->
    
    @size 640, 360
    @colorMode @RGB, @width
    @a = 0
    @b = @width
    @direction = yes
    
    @frameRate 30


draw: ->
    
    @a++
    if @a > @width
        @a = 0
        @direction = not @direction
    
    @stroke if @direction then @a else @width-@a
    
    @line @a, 0, @a, @height/2
    
    @b--
    @b = @width if @b < 0
    @stroke if @direction then @width-@b else @b
    
    @line @b, @height/2+1, @b, @height

