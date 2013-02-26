(let (
      (trace-tags "info5,warning,msg,iRobot,-boundSymbols,-policies9,-commitments,-eventqueue,-conversations")
      (trace-code 10) ; bit 1=off, 2=on, 4=to-monitor-window, 8=trace-to-file
      (sleep-time 2) ; time to sleep between starting dependent agents, adjust for slower machines
      )

(agent.new-agent "api.Simulator" 
                 "George" 
                 5781  
                 :LAC 9000 
                 :process "CURRENT" 
                 :trace trace-code 
                 :traceTags trace-tags 
                 :markup "KQML" 
                 :outstream "sim.out" 
                 :instream "sim.in"
                 :interface "none")
(agent.tell ":5780" 
            "(iRobot-env.triangle \"George\" :name \"red-tri\" :color-name \"red\")")
)