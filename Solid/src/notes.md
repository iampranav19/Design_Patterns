SOLID + DESIGN PATTERNS + PRINCIPLES CHEAT SHEET
============================================
Repo layout: every topic has a `bad/` (violation) and `good/` (fixed) package,
each with a runnable Main.java. Pattern for every demo:
  1. Show the BAD code, run BAD Main -> point out the smell (or the crash).
  2. Show the GOOD code, run GOOD Main -> point out what changed and why it's safer.

Every entry below follows the same shape:
  Problem  = the specific thing that's wrong in the code
  Why bad  = what breaks / what it costs if this ships
  Fix      = what the good/ version does differently
  Manager  = how to frame it in business terms

Opening line: "None of this is academic — each one maps to a real bug class
we've hit before: rigid code, runtime crashes, fragile refactors, wasted effort."


S — Single Responsibility Principle
------------------------------------
Files:  src/srp/bad/Employee.java  vs  src/srp/good/{Employee,EmployeeRepository,EmployeeReportGenerator,Main}.java
Run:    java -cp out srp.good.Main   (bad has no Main — it's a design smell, not a crash)

Say: "A class should have ONE reason to change."

Problem:
  - Employee.java does 3 unrelated jobs: hold data, save to DB, print a report.

Why bad:
  - A report-format tweak and a database change both touch the same file.
  - Editing one job risks silently breaking a different job.

Fix:
  - Split into 3 classes: Employee (data), EmployeeRepository (save),
    EmployeeReportGenerator (report).
  - Each one only changes for its own reason.

Manager takeaway:
  - Smaller, safer code reviews — you can review "the report change"
    without touching DB logic.


O — Open/Closed Principle
---------------------------
Files:  src/ocp/bad/{Order,ShippingCalculator,Main}.java  vs  src/ocp/good/{ShippingStrategy,StandardShipping,ExpressShipping,InternationalShipping,DroneShipping,ShippingCalculator,Main}.java
Run:    java -cp out ocp.bad.Main
        java -cp out ocp.good.Main

Say: "Code should be open for extension, but closed for modification."

Problem:
  - ShippingCalculator.calculate() is one long if/else on a shippingType string.

Why bad:
  - Adding "Drone" shipping means opening this already-tested class again.
  - One new branch risks breaking Standard, Express, and International too.

Fix:
  - A ShippingStrategy interface, with one small class per shipping type.
  - Calculator just calls strategy.calculate() — it never changes again.
  - Adding DroneShipping.java touches zero existing files.

Manager takeaway:
  - New features become new files, not risky edits to shared, tested code.
  - Less regression risk every time we ship a new option.


L — Liskov Substitution Principle
-----------------------------------
Files:  src/lsp/bad/{Payment,CryptoPayment,Main}.java  vs  src/lsp/good/{Payment,Refundable,CardPayment,CryptoPayment,Main}.java
Run:    java -cp out lsp.good.Main   (runs clean)
        java -cp out lsp.bad.Main    (CRASHES on purpose — good demo moment)

Say: "A subtype must work anywhere its parent type is expected, without
      breaking the caller."

Problem:
  - CryptoPayment extends Payment, but crypto can't be refunded.
  - It's forced to override refund() and just throw an exception.

Why bad:
  - Any code that trusts "every Payment can refund" crashes the moment it
    hits a CryptoPayment — at runtime, in production.

Fix:
  - Split into two interfaces: Payment (processPayment) and
    Refundable (refund).
  - CryptoPayment only implements what it can honestly do.
  - Callers check `instanceof Refundable` instead of assuming.

Manager takeaway:
  - This is the "surprise production crash" principle.
  - A subclass promising behavior it can't deliver is a ticking time bomb.


I — Interface Segregation Principle
--------------------------------------
Files:  src/isp/bad/{UberDriver,RideDriver,FoodDeliveryDriver,Main}.java  vs  src/isp/good/{RideDriver,FoodDeliveryDriver,UberXDriver,UberEatsDriver,UberProDriver,Main}.java
Run:    java -cp out isp.good.Main   (runs clean)
        java -cp out isp.bad.Main    (CRASHES on purpose)

Say: "Don't force a class to implement methods it doesn't use."

Problem:
  - UberDriver is one fat interface: ride methods + food-delivery methods.
  - A pure ride driver is forced to "implement" delivery methods it can't do,
    just by throwing an exception.

Why bad:
  - Calling the full contract on a ride-only driver crashes at runtime —
    same failure shape as the LSP example, caused by a too-broad interface.

Fix:
  - Split into RideDriver and FoodDeliveryDriver interfaces.
  - UberXDriver implements only rides, UberEatsDriver only deliveries,
    UberProDriver implements both.

Manager takeaway:
  - This is Single Responsibility applied to interfaces.
  - Keeps contracts honest — no more fake implementations that throw.


D — Dependency Inversion Principle
--------------------------------------
Files:  src/dip/bad/{RecentlyAddedRecommender,TrendingNowRecommender,GenreBasedRecommender,RecommendationEngine,Main}.java
        vs  src/dip/good/{RecommendationSource,...same 3 recommenders...,RecommendationEngine,Main}.java
Run:    java -cp out dip.bad.Main
        java -cp out dip.good.Main

Say: "High-level modules shouldn't depend on low-level modules — both should
      depend on an abstraction."

Problem:
  - RecommendationEngine directly creates `new TrendingNowRecommender()`
    inside itself.

Why bad:
  - Switching to Genre-Based recommendations means opening and editing
    RecommendationEngine.java itself.
  - The engine can't be unit-tested with a fake recommender.

Fix:
  - Add a RecommendationSource interface.
  - RecommendationEngine takes any RecommendationSource via its constructor.
  - Main can wire up Recently Added / Trending Now / Genre-Based without
    touching RecommendationEngine.java at all.

Manager takeaway:
  - This is what makes code testable (inject a fake) and configurable
    (swap strategies at runtime — e.g. A/B testing recommendation algorithms).


========= DESIGN PATTERNS =========

Singleton Pattern
------------------
Files:  src/singleton/bad/{DatabaseConnectionManager,Main}.java  vs  src/singleton/good/{DatabaseConnectionManager,Main}.java
Run:    java -cp out singleton.bad.Main
        java -cp out singleton.good.Main

Say: "Some things — a connection pool, a config store — should only ever
      exist once in the running app."

Problem:
  - DatabaseConnectionManager has a public constructor.
  - Nothing stops `new DatabaseConnectionManager()` being called anywhere.

Why bad:
  - Two "new"s create two independent pools with independent state.
  - `pool1 == pool2` is false, and their connection counts drift apart.

Fix:
  - Make the constructor private.
  - Add a static getInstance() that always returns the same instance.
  - `pool1 == pool2` is now true.

Manager takeaway:
  - Prevents "why do we have two pools with different state" bugs.
  - One shared source of truth, guaranteed.


Factory Pattern
-----------------
Files:  src/factory/bad/{Logistics,Road,Sea,Air,Main}.java  vs  src/factory/good/{Logistics,Road,Sea,Air,LogisticsFactory,Main}.java
Run:    java -cp out factory.bad.Main
        java -cp out factory.good.Main

Say: "Centralize object creation instead of scattering if/else new-ing logic
      across the codebase."

Problem:
  - Main has an if/else on transportType that directly `new`s up
    Road / Sea / Air itself.

Why bad:
  - That same if/else has to be copy-pasted anywhere else a Logistics
    object is needed.
  - Every new transport type means finding and editing all those copies.

Fix:
  - LogisticsFactory.create(type) becomes the ONLY place that knows about
    Road / Sea / Air.
  - Callers only ever talk to Logistics + the factory.

Manager takeaway:
  - Adding a transport type is a one-file change, not a codebase-wide
    search-and-replace.


Builder Pattern
------------------
Files:  src/builder/bad/{Pizza,Main}.java  vs  src/builder/good/{Pizza (+ nested Builder),Main}.java
Run:    java -cp out builder.bad.Main
        java -cp out builder.good.Main

Say: "When an object has a lot of optional parameters, stop passing them all
      positionally."

Problem:
  - Pizza has one constructor with 6 positional params, mostly booleans:
    `new Pizza("Large", true, true, false, true, false)`.

Why bad:
  - You can't tell what the 3rd `true` means without opening the constructor.
  - It's easy to swap two booleans and silently build the wrong pizza.

Fix:
  - Build it through a fluent Builder:
    `.cheese().pepperoni().extraCheese().build()`.
  - Only call the methods for what you actually want; order doesn't matter.

Manager takeaway:
  - Self-documenting construction — no more guessing what the 5th `true` means.


Prototype Pattern
--------------------
Files:  src/prototype/bad/{EmailTemplate,Main}.java  vs  src/prototype/good/{EmailTemplate,Main}.java
Run:    java -cp out prototype.bad.Main    (~1.4s for 3 emails)
        java -cp out prototype.good.Main   (~0.5s for 3 emails)

Say: "If building an object is expensive but most copies are nearly
      identical, build it once and clone it."

Problem:
  - EmailTemplate redoes the full expensive build every time: HTML skeleton,
    branding, CSS — even though the emails are 95% identical.

Why bad:
  - That's wasted, repeated work. 3 emails take ~1.4s, even though the only
    real difference between them is the recipient's name.

Fix:
  - EmailTemplate implements Cloneable.
  - The expensive build runs ONCE to create a prototype.
  - Every email after that is `prototype.clone()` + `personalizeFor(name)` —
    ~0.5s for the same 3 emails.

Manager takeaway:
  - Real, measurable perf win for "same template, many recipients" work
    (welcome emails, notifications, reports).


Adapter Pattern
------------------
Files:  src/adapter/bad/{PaymentProcessor,StripeGateway,Main}.java  vs  src/adapter/good/{PaymentProcessor,StripeGateway,StripePaymentAdapter,Main}.java
Run:    java -cp out adapter.bad.Main
        java -cp out adapter.good.Main

Say: "Wrap a third-party API's incompatible interface instead of letting its
      quirks leak into your business logic."

Problem:
  - StripeGateway.makeCharge(int amountInCents) doesn't match our
    PaymentProcessor.pay(double amountInDollars) contract.

Why bad:
  - Every caller has to remember to convert dollars to cents itself.
  - StripeGateway can't be passed anywhere a PaymentProcessor is expected —
    it just doesn't implement that interface.

Fix:
  - StripePaymentAdapter implements PaymentProcessor.
  - The cents conversion lives in exactly one place.
  - checkout() only ever talks to PaymentProcessor — no idea Stripe exists.

Manager takeaway:
  - This is how we swap payment/shipping/analytics vendors later without
    rewriting checkout logic — the adapter absorbs the change.


Facade Pattern
------------------
Files:  src/facade/bad/{InventoryService,PaymentService,ShippingService,NotificationService,Main}.java
        vs  src/facade/good/{...same 4 services...,CheckoutFacade,Main}.java
Run:    java -cp out facade.bad.Main
        java -cp out facade.good.Main

Say: "Give a complex subsystem one simple front door instead of making every
      caller learn its internals."

Problem:
  - Main directly calls Inventory, Payment, Shipping, and Notification
    services itself, in the exact right order, with the right guard checks.

Why bad:
  - Every place an order can be placed — web, mobile, admin — has to know
    all 4 subsystems and get that sequence exactly right.
  - Miss a step, or check payment before stock, and you've shipped a bug.

Fix:
  - CheckoutFacade.placeOrder(...) wraps all 4 calls, in order, behind one
    method.
  - Callers just call placeOrder() and get back a boolean.

Manager takeaway:
  - One correct implementation of "how to place an order," reused
    everywhere — instead of N copies that can each drift out of sync.


========= OTHER PRINCIPLES (beyond SOLID) =========

DRY — Don't Repeat Yourself
-------------------------------
Files:  src/dry/bad/{Order,Invoice,Main}.java  vs  src/dry/good/{TaxCalculator,Order,Invoice,Main}.java
Run:    java -cp out dry.bad.Main    (BUG: prints two different totals)
        java -cp out dry.good.Main   (totals match)

Say: "Duplication isn't just ugly — it's a bug waiting to drift out of sync."

Problem:
  - Order and Invoice each hardcode their own TAX_RATE and duplicate the
    same tax formula.

Why bad:
  - Order's rate got bumped to 8%; Invoice was never updated, still at 5%.
  - Same $100 subtotal -> $108 in one place, $105 in the other.

Fix:
  - One shared TaxCalculator.calculateTotal().
  - Order and Invoice both call it — only one rate to update, ever.

Manager takeaway:
  - This is a real production bug pattern, not a style complaint.
  - Duplicated logic WILL drift — it's a question of when.


KISS — Keep It Simple, Stupid
----------------------------------
Files:  src/kiss/bad/{FreeShippingRule,MinimumOrderAmountRule,FreeShippingRuleFactory,FreeShippingEvaluator,Main}.java (5 files)
        vs  src/kiss/good/{ShippingCalculator,Main}.java (2 files)
Run:    java -cp out kiss.bad.Main
        java -cp out kiss.good.Main
        (both print the identical result — that's the point)

Say: "Don't build machinery for a question that has a one-line answer."

Problem:
  - The bad version answers "is orderTotal >= 50?" using an interface, a
    rule class, a factory, and a generic multi-rule evaluator — for a list
    that only ever holds one rule.

Why bad:
  - None of that machinery is needed. It's more code to read, test, and
    maintain, for the exact same answer one comparison already gives you.

Fix:
  - ShippingCalculator.isEligibleForFreeShipping(orderTotal) — one static
    method, one line of logic, same output.

Manager takeaway:
  - Complexity has a cost even when it "works" — every extra file is more
    surface area for the next dev to get lost in.


YAGNI — You Aren't Gonna Need It
-------------------------------------
Files:  src/yagni/bad/{ReportExporter,CsvReportExporter,PdfReportExporter,XmlReportExporter,ExcelReportExporter,ReportExporterFactory,Main}.java (7 files)
        vs  src/yagni/good/{CsvReportExporter,Main}.java (2 files)
Run:    java -cp out yagni.bad.Main
        java -cp out yagni.good.Main
        (both print the identical result — same as KISS, different root cause)

Say: "Don't build for a requirement that doesn't exist yet — build for the
      one you actually have."

Problem:
  - The bad version ships Pdf/Xml/Excel exporters "just in case," alongside
    a factory built to support all 4 formats — but only CSV was ever
    requested, and Main only ever uses CSV.

Why bad:
  - The 3 extra exporters are untested, unused dead code that still has to
    be kept in sync every time the report's data model changes.
  - Real maintenance cost for a requirement nobody has actually asked for.

Fix:
  - The good version has exactly one exporter: CsvReportExporter.
  - If PDF gets requested next quarter, add PdfReportExporter then.

Manager takeaway:
  - KISS vs YAGNI: KISS over-solves the requirement you HAVE; YAGNI solves
    requirements you DON'T have yet.
  - Same symptom (bloat), different root cause — both cost real time for
    zero current value.


How to run any demo
---------------------
From the project root:
  javac -d out $(find src/<topic> -name "*.java")     # e.g. src/facade
  java -cp out <topic>.<bad|good>.Main                # e.g. facade.good.Main

Closing line: "Every 'good' package here is a drop-in replacement for its
'bad' twin — same behavior, but each violation we fixed removes a specific
class of bug: tangled changes (S), risky edits (O), runtime crashes from
broken substitutability (L), fake/throwing implementations (I), untestable
rigid wiring (D), duplicated logic that drifts (DRY), and unnecessary
complexity for problems we do or don't yet have (KISS/YAGNI). The patterns
(Singleton, Factory, Builder, Prototype, Adapter, Facade) are the reusable
tools we reach for to get from the bad column to the good one."