package sample;


import java.util.*;
class Caesar
{
	/*public static void main(String args[])
	{
		System.out.println("Enter a string :");
		String s;
		Scanner x=new Scanner(System.in);
		s=x.nextLine();
		System.out.println("Enter a key :");
		int key=x.nextInt();
		String ct=encrypt(s,key);
		decrypt(ct,key);
	}*/
	public static String encrypt(String s,int key)
	{
		char c;
		String s1="";
		for(int i=0;i<s.length();i++)
		{
			if(s.charAt(i)!=' ')
			{
				s1+=s.charAt(i);
			}
		}
		s=s1;
		s=s.toLowerCase();
		String ct="";
		for(int i=0;i<s.length();i++)
		{
			c=s.charAt(i);
			int k2;
			int k1=(int)(c);	
			k2=(char)(((k1+key)-97)%26)+97;
			ct+=((char)k2);
		}
		System.out.println("Cipher text :"+ct);	
		return ct;
	}
	public static String decrypt(String s,int key)
	{
		String dec="";
		char c;
		for(int i=0;i<s.length();i++)
		{
			c=(char)s.charAt(i);
			int n=(int)c-key;
			if(n<97)
				n+=26;
			dec+=(char)n;
			
		}
		System.out.println(dec);
		return dec;
	}
}
