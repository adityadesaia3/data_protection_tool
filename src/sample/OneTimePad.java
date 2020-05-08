package sample;

import java.util.*;
class OneTimePad
{
	//public static String key="";
	/*public static void main(String args[])
	{
		String s;
		Scanner x=new Scanner(System.in);
		System.out.println("enter a string :");
		s=x.nextLine();
		
		String encry=encrypt(s);
		decrypt(encry,key);
	}*/
	public static String encrypt(String s, String key)
	{
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

		int k=-1;
		int one,two;
		String encry="";
		for(int i=0;i<s.length();i++)
		{
			one=(int)s.charAt(i)-97;
			k++;
			k=(k)%key.length();
			two=(int)key.charAt(k)-97;
			System.out.println(((one+two))%26);
			encry+=(char)(((one+two)%26)+97);
		}
		System.out.println("encrypted :"+encry);
		return encry;
	}
	public static String decrypt(String s,String key)
	{
		int k=-1;
		int one,two;
		int three;
		String decry="";
		for(int i=0;i<s.length();i++)
		{
			one=(int)s.charAt(i)-97;
			k++;
			k=(k)%key.length();
			two=(int)key.charAt(k)-97;
			three=(one-two);
			if(three<0)
			{
				three+=26;
			}
			System.out.println((three)+97);
			decry+=(char)((three)+97);
		}
		System.out.println("decrypted :"+decry);
		return decry;
	}
}