use CGI;
$query  = new CGI;

$asin  = $query->param( 'asin' );
$title  = $query->param( 'title' );
$price  = $query->param( 'price' );
@developers  = $query->param( 'developers' );



  # Remove leading and trailing spacing.
  $username =~ s/^\s*(\S*)\s*$/$1/;
  # For security, remove some Unix metacharacters.
  $username =~ s/;|>|>>|<|\*|\?|\&|\|//g;
  
  # Remove leading and trailing spacing.
  $password =~ s/^\s*(\S*)\s*$/$1/;
  # For security, remove some Unix metacharacters.
  $password =~ s/;|>|>>|<|\*|\?|\&|\|//g;

$cmd    =  "/usr/bin/java -Djava.security.egd=file:/dev/./urandom saveGame_zqw";
$cmd   .= " '". $asin . "'";
$cmd   .= " '". $title . "'";
$cmd   .= " '". $price . "'";
$cmd   .= " ";
foreach $dev (@developers) {
	$cmd .= "'". $dev . "' ";
}

#print( $cmd );
system( $cmd );
my $referrer = $ENV{HTTP_REFERER};
print $query->redirect($referrer);



