<%--
  Created by IntelliJ IDEA.
  User: pliu
  Date: 7/18/16
  Time: 4:18 PM
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>

    <meta name="layout" content="main"/>
    <title>eTRIKS labs Hi dome</title>
</head>

<body>

<div class="container">
    <div class="row">
        <h1>Hi dome</h1>
    </div>

    <div class="row">
        <div class="col-md-12">
            <h3>1.What the tool can do for you?</h3>

            The core objective of this delivery is to allow researchers to create patient cohorts by setting
            constraints on components in high dimensional data sets, e.g. select all patients with expression
            value of a particular gene greater than a user-selected threshold. Additionally, the values for these
            components can be viewed like regular numerical concepts in the Summary Statistics and Grid View tabs.
            To this end, the tranSMART web application will be extended with GUI features that will allow researchers
            to specify constraints over these components. By allowing researchers to directly compare clinical data with
            high dimensional data, the integration of HiDome in tranSMART will expedite translational research.
            <br> 
            <br>
            HiDome will directly impact the way projects can make use of high dimensional data in their studies.
            The analysis of this data was until now limited to the Advanced Analysis tab. With HiDome, researchers
             will be able to create cohorts based on constraints set on high dimensional data, e.g. select all patients
             which are male and have a log expression of the KRAS gene in their CD4 cells between -0.1 and 1.5. Additionally,
             researchers will have the opportunity to explore and compare the distribution of components within high dimensional
              datasets between subsets, in the same way that is currently available for numerical concepts. Finally the selected
              data will also be viewable in the Grid View tab, and it can be exported together with the clinical data.
            <br>

        </div>
    </div>
    <hr>

    <div class="row">
        <div class="col-md-12">
            <h3>2.How to get the tool</h3>
            You can download a pre-compiled WAR file here (link still to come).
            If you have a Transmart instance already running, you can use this WAR file as a drop-in replacement. Otherwise please refer to the Transmart installation instructions.
           <br>
            <br>
            The source code is available at <a href="https://github.com/dennyverbeeck"> https://github.com/dennyverbeeck</a>, and consists of three projects:
              <ul>
            <li>transmart-core-api</li>
            <li>transmart-core-db</li>
            <li>transmartApp</li>
            </ul>
            all of which have a branch named feature/hidome. To install from source you should have Grails v3.11 and Maven installed on your system.
            You can use the following script to compile a WAR file from latest source.
            <br>
            <br>
            <code type="bash">
                cd ~ <br>
                mkdir hidome && cd hidome
                <br>
                <br>
                 
                # check out and install transmart-core-api plugin to local maven repository<br>
                git clone https://github.com/dennyverbeeck/transmart-core-api.git<br>
                cd transmart-core-api <br>
                git checkout feature/hidome<br>
                mvn install <br>
                cd .. <br>
                <br>
                 
                # check out and compile web application <br>
                git clone https://github.com/dennyverbeeck/transmart-core-db.git <br>
                cd transmart-core-db <br>
                git checkout feature/hidome <br>
                cd .. <br>
                git clone https://github.com/dennyverbeeck/transmartApp.git <br>
                cd transmartApp <br>
                git checkout feature/hidome <br>
                grails war <br>
</code>
        </div>
    </div>
    <hr>


    <div class="row">
        <div class="col-md-12">
            <h3>3.Learn how to use the tool</h3>


        </div>
    </div>

    <div class="row">


        <div class="col-md-12">
            HiDome can be used in a variety of ways. On a HiDome-enabled installation of Transmart you can drag and drop a
            high-dimensional data node either into the cohort selection tool, in the summary statistics tabs or the grid view
            tab to access the high-dimensional data. In cohort selection, it can be used to further refine patient cohorts.
             For instance, you may be interested to divide your patients into a group with low expression of the TNF gene,
            and a group with high expression of the TNF gene. You can combine these constraints with clinical data constraints
            such as age. You can also use HiDome to explore the distribution of expression values in patient subsets. To do this,
            simply drag the high dimensional node to the summary statistics tab after creating your subsets, and indicate the gene
            of interest. If your study contains multiple high dimensional samples, multiple platforms, or even multiple data types,
            you can use HiDome to explore relations between all of these. For example, using HiDome you can create two subsets based
            on gene expression, and explore the effect this has the distribution of metabolomics or proteomics data.
        </div>
    </div>


    <hr>

    <div class="row">
        <div class="col-md-12">
            <h3>4.How to get Involved</h3>
            Please feel free to contact <a href="mailto:dverbeec@its.jnj.com" target="_top">Denny Verbeeck</a> with any bug reports, feature requests or other comments relating to HiDome.
        </div>
    </div>
    <hr>


    <div class="row">
        <div class="col-md-12">
            <h3>5.Disclaimer</h3>
            HiDome is offered to the public as freely available resource, for non-commercial research use. Some aspects of this
            experimental module may still be under development, and there are no warranties about the completeness, reliability, accuracy,
            and security of the software package. Please bear this in mind, especially if you wish to analyse personal and/or confidential data.
        </div>
    </div>
<hr>

    <div class="row">
        <div class="col-md-12">
            <h3>6.Acknowledgements</h3>
            The authors would like to acknowledge the contributions of the IMI and EFPIA.
            Special thanks to Johnson & Johnson, Bayer, Imperial College London,
            University of Luxembourg and TheHyve for their respective support on HiDome.
        </div>
    </div>

    %{--End of container--}%
</div>

%{--import javascript--}%
<script src="${request.contextPath}/js/videojs/video.js"></script>
<script>
    videojs.options.flash.swf = "${request.contextPath}/js/videojs/video-js.swf"
</script>
</body>
</html>