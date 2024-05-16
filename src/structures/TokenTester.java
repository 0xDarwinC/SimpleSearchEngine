package structures;

public class TokenTester {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String delims = ": ,.!";
		String query = "Find cats, dogs, and Fish!";
		Tokenizer baller = new Tokenizer(delims, query);
		String answer = "";
		while(baller.hasToken()) {
			try{
				answer+= "\n" +  baller.nextToken();
			}
			catch(Exception e){
				e.printStackTrace();
			}
		}
		System.out.println(answer);
		
	}
}
