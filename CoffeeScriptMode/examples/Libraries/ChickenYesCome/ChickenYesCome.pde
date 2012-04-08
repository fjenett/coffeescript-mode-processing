###
Permutation example of Combinatorics library

Get library here:
https://github.com/fjenett/combinatorics
###

#import de.bezier.math.combinatorics.*

setup: ->

    @size 400, 200
    
    @perm = new Permutation 3
    @words = ['Chicken', 'Yes', 'Come']
    
    @textFont @createFont 'Helvetica', 62
    @fill 0
    
    @next = yes
    @noLoop()

draw: ->
    
    if ( @next )
        
        @background 255
        
        p = @perm.next()
        y = 56
        for n in p
            @text @words[n], 16, y
            y += 64
            
        @next = no

mouseReleased: ->
    
    @next = yes
    @redraw()
    
keyPressed: ->
    
    @perm.rewind() unless @perm.hasMore()
    @next = yes
    @redraw()
