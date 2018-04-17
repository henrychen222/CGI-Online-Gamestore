#!/usr/bin/perl
use CGI;
$query  = new CGI;

$developers = $query->param( 'developers' );


if($developers){
	  # Remove leading and trailing spacing.
	  $developers =~ s/^\s*(\S*)\s*$/$1/;
	  # For security, remove some Unix metacharacters.
	  $developers =~ s/;|>|>>|<|\*|\?|\&|\|//g;
	  
	  
	  $cmd    =  "/usr/bin/java -Djava.security.egd=file:/dev/./urandom searchGames_zqw";
	  $cmd   .= " '". $developers . "'";
	  
	  system( $cmd );
  
}else{
	#测试下到底developer为空传过来是什么？
	$cmd    =  "/usr/bin/java -Djava.security.egd=file:/dev/./urandom searchGames_zqw";
	
	system( $cmd );

}




