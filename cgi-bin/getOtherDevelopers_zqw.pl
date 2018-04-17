#!/usr/bin/perl
use CGI;
$query  = new CGI;

$Asin = $query->param( 'Asin' );

if($Asin){
	  # Remove leading and trailing spacing.
	  $Asin =~ s/^\s*(\S*)\s*$/$1/;
	  # For security, remove some Unix metacharacters.
	  $Asin =~ s/;|>|>>|<|\*|\?|\&|\|//g;
	  
	  
	  $cmd    =  "/usr/bin/java -Djava.security.egd=file:/dev/./urandom getOtherDevelopers_zqw";
	  $cmd   .= " '". $Asin . "'";
	  
	  #print $cmd;
	  system( $cmd );
	  
  
}else{
	$cmd    =  "/usr/bin/java -Djava.security.egd=file:/dev/./urandom getOtherDevelopers_zqw";
	
	#print $cmd;
	system( $cmd );
	 

}
#print $Asin;