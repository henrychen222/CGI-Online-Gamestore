#!/usr/bin/perl
use CGI;
$query  = new CGI;

$Asin = $query->param( 'Asin' );

print $Asin;
if($Asin eq "0003"){
	  # Remove leading and trailing spacing.
	  $Asin =~ s/^\s*(\S*)\s*$/$1/;
	  # For security, remove some Unix metacharacters.
	  $Asin =~ s/;|>|>>|<|\*|\?|\&|\|//g;
	  
	  
	  $cmd    =  "/usr/bin/java -Djava.security.egd=file:/dev/./urandom getGames_zqw";
	  $cmd   .= " '". $Asin . "'";
	  
	  system( $cmd );
  
}else{
	$cmd    =  "/usr/bin/java -Djava.security.egd=file:/dev/./urandom getGames_zqw";
	
	system( $cmd );

}







  
  # Remove leading and trailing spacing.
  $password =~ s/^\s*(\S*)\s*$/$1/;
  # For security, remove some Unix metacharacters.
  $password =~ s/;|>|>>|<|\*|\?|\&|\|//g;

$cmd    =  "/usr/bin/java -Djava.security.egd=file:/dev/./urandom saveGame_zqw";
$cmd   .= " '". $asin . "'";
$cmd   .= " '". $title . "'";
$cmd   .= " '". $price . "'";