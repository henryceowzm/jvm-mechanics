package example12;

import support.Output;
import support.RunWith;

/**
 * Created by henry on 10/11/17.
 */
@RunWith({
        "-Xbatch", "-XX:-TieredCompilation", "-XX:+PrintCompilation",
        "-XX:+UnlockDiagnosticVMOptions",
        "-XX:CompileCommand=print,example12/Tiny::tiny",
        "-XX:CompileCommand=print,example12/Tiny::main"
})
@Output(highlight={"Tiny::tiny", "made not entrant", "4e20"})
public class Tiny {
    public static void main(String[] args) {
        for (int i = 0; i < 20_000; i++) {
            tiny(i);
        }
    }

    public static int tiny(int i) {
        return i + 1;
    }
}
