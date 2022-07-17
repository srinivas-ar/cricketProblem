package com.simpl.assignment

class DataLayer {

    fun playerDataLoad():ArrayList<Player> {

        var list=ArrayList<Player>()

        var p1:Player= Player()
        p1.playerName="Kirat Boli"
        p1.score=0;
        p1.frequency=intArrayOf(5, 30, 25, 10, 15, 1, 9, 5)

        //5,35,60,70,85,86,95,100


        var p2:Player= Player()
        p2.playerName="N.S Nodhi"
        p2.score=0;
        p2.frequency=intArrayOf(10, 40, 20, 5, 10, 1, 4, 10)


        var p3:Player= Player()
        p3.playerName="R Rumrah"
        p3.score=0;
        p3.frequency=intArrayOf(20, 30, 15, 5, 5, 1, 4, 20)


        var p4:Player= Player()
        p4.playerName="Shashi Herna"
        p4.score=0;
        p4.frequency=intArrayOf(30, 25, 5, 0, 5, 1, 4, 30)

        list.add(p1)
        list.add(p2)
        list.add(p3)
        list.add(p4)
        return  list



    }
}