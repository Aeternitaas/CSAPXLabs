def beerOnWall():
    beerbottles = 99
    while beerbottles > 0:
        print( str(beerbottles) + " of beer on the wall, " + str(beerbottles) + " bottles of beer. Take one down, pass it around, " + str(beerbottles-1) + " bottles of beer on the wall.")
        beerbottles = beerbottles - 1

beerOnWall()

