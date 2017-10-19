package example14a;

import support.Output;
import support.RunWith;

@RunWith({
        "-XX:+TieredCompilation", "-XX:TieredStopAtLevel=1", "-XX:+PrintCompilation",
        "-XX:+UnlockDiagnosticVMOptions", "-XX:+PrintInlining",
        "-XX:CompileCommand=print,example14a/MethodSmallEnoughForInline::shouldInline",
        "-XX:CompileCommand=print,example14a/MethodSmallEnoughForInline::inlineCandidateCaller"
})
@Output(highlight={
        // highlights for PrintCompilation / PrintInlining
        "MethodSmallEnoughForInline::shouldInline","MethodSmallEnoughForInline::inlineCandidateCaller"
})
public class MethodSmallEnoughForInline
{
    public static void main(final String[] args) throws Exception
    {
        long accumulator = 17L;

        for(int i = 0; i < 260; i++)
        {
            accumulator += inlineCandidateCaller(accumulator);
        }
        System.out.println("About to exceed MinInliningThreshold");

        for(int i = 0; i < 20; i++)
        {
            accumulator += inlineCandidateCaller(accumulator);
        }

        Thread.sleep(5000L);

        System.out.println("DCE guard: " + accumulator);
    }

    private static long inlineCandidateCaller(final long input)
    {
        return shouldInline(input);
    }

    private static long shouldInline(final long input)
    {
        return (input * System.nanoTime()) + 37L;
    }
}