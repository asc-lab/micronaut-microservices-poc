set -e

BASE=`dirname $0`
SERVICES=`find ${BASE}/../*/build.sh`

for SRV in ${SERVICES} ;
do
    (
    cd `dirname ${SRV}`
    pwd
    ./build.sh
    )
done
