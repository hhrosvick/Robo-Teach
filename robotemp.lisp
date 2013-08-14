(a 0)
(loop
 (a (+ a 1))
 (irobot.moveby 200)
 (when (irobot.error) (irobot.moveby -50))
 (irobot.rotate-deg -90)
 (when (> a 8) (return))
)
