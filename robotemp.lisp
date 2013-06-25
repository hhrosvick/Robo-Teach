(a 0)
(loop
 (a (+ a 1))
 (irobot.moveby 200)
 (irobot.rotate-deg -90)
 (when (> a 3) (return))
)