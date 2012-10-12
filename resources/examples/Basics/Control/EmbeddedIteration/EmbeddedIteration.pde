###
Embedding Iteration. 

Embedding "for" structures allows repetition in two dimensions. 
###

setup: ->

    size 640, 360
    background 0
    noStroke()
    
    gridSize = 40
    
    for x in [gridSize..(width-gridSize)] by gridSize
        
        for y in [gridSize..(height-gridSize)] by gridSize
            
            noStroke()
            fill 255
            rect x-1, y-1, 3, 3
            stroke 255, 50
            line x, y, width/2, height/2
   

