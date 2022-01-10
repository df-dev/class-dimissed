public class bad {
    public bad(){
        System.out.println("executing bad class");
            try 
                { String[] cmd = {"touch","/tmp/bad"};
                java.lang.Runtime.getRuntime().exec(cmd).waitFor();
            } catch (Exception e) {
                e.printStackTrace();
            } System.out.println("completed execution");
    }
}