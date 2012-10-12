###
Array.
An array is a list of data. Each piece of data in an array 
is identified by an index number representing its position in 
the array. Arrays are zero based, which means that the first 
element in the array is [0], the second element is [1], and so on. 
In this example, an array named "coswav" is created and
filled with the cosine values. This data is displayed three 
separate ways on the screen.
###

setup: ->
    size 640, 360
    @coswave = []
    for i in [0...width]
        amount = map i, 0, width, 0, PI
        @coswave.push abs( cos( amount ) )
    
    noLoop

draw: ->
    for v, i in @coswave
        stroke v * 255
        line i, 0, i, height/3
    
    for v, i in @coswave
        stroke (v * 255) / 4
        line i, height/3, i, height/3*2
    
    for v, i in @coswave
        stroke 255 - v * 255
        line i, height/3*2, i, height
